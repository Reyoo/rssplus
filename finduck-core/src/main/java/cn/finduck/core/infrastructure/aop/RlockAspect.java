package cn.finduck.core.infrastructure.aop;

import cn.finduck.core.infrastructure.annotation.Rlock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/**
 * @author heiyaoshi
 * @Desc:分布式锁切面
 * @version:1.0
 * @Date: 2021/5/14 17:21
 */
@Slf4j
@Aspect
@Component
public class RlockAspect {

    private static final String POUND_KEY = "#";

    @Resource
    private RedissonClient redissonClient;

    @Pointcut("@annotation( cn.finduck.core.infrastructure.annotation.Rlock)")
    public void pointcut(){}

    @Around("pointcut()")
    public <T> T around(ProceedingJoinPoint point) throws Throwable{
        T obj = null;
        RLock lock = null;
        try {
            Rlock rlockInfo = getRlockInfo(point);
            lock = redissonClient.getLock(getLocalKey(point, rlockInfo));
            log.info("====================获取锁======================");
            if(lock != null){
                final boolean flag = lock.tryLock(rlockInfo.waitTime(), rlockInfo.leaseTime(), rlockInfo.timeUnit());
                if(flag){
                    obj = (T) point.proceed();
                }else {
                    throw new RuntimeException("=============获取锁失败================");
                }
            }
        }finally {
            if(lock != null && lock.isHeldByCurrentThread()){
                lock.unlock();
                log.info("================释放锁======================");
            }
        }
        return obj;
    }

    private String getLocalKey(ProceedingJoinPoint point, Rlock rlockInfo) throws Exception {
        String localKey = rlockInfo.localKey();
        if(!localKey.contains(POUND_KEY)){
            return localKey;
        }
        String[] split = localKey.split("#");
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        final Object[] args = point.getArgs();
        int paramIndex = ArrayUtils.indexOf(parameterNames, split[1]);
        if(paramIndex != -1){
            return split[0] + args[paramIndex];
        }
        for(Object arg : args){
            if(hasFiled(arg,split[1])){
                Method method = arg.getClass().getMethod("get" + getMethodName(split[1]));
                Object invoke = method.invoke(arg);
                localKey = split[0] + invoke;
                break;
            }
        }
        return localKey;
    }


    private Rlock getRlockInfo(ProceedingJoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        return methodSignature.getMethod().getAnnotation(Rlock.class);
    }

    private static String getMethodName(String filedName){
        byte[] items = filedName.getBytes(StandardCharsets.UTF_8);
        items[0] = (byte) ((char)items[0] - 'a' + 'A');
        return new String(items);
    }

    private static boolean hasFiled(Object obj, String fieldName){
        if(obj == null || StringUtils.isEmpty(fieldName)){
            return false;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields){
            if(fieldName.equals(field.getName())){
                return true;
            }
        }
        return false;
    }


}
