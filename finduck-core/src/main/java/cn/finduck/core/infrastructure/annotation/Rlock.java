package cn.finduck.core.infrastructure.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Desc:分布式锁注解
 * @author QiSun
 * @version:1.0
 * @Date: 2021/5/14 17:14
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Rlock {

    /**
     * 分布式锁的key
     * @return
     */
    String localKey() default "redisLockAnnotation::";

    /**
     * 等待时间 默认5s
     * @return
     */
    long waitTime() default 5;

    /**
     * 释放时间 默认10s
     * @return
     */
    long leaseTime() default 10;

    /**
     * 时间格式 默认：秒
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
