package cn.finduck.core.infrastructure.discovery;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author luocy
 * @description Cache Discovery Client
 * @create 2021-04-28
 * @since 1.0
 */
@Slf4j
@Component
public class CacheDiscoveryClient {

    /**
     * 安全的
     */
    public final static String SECURED = "https://";

    /**
     * 非安全的
     */
    public final static String NO_SECURED = "http://";

    /**
     * 分隔符
     */
    private final static String SEMICOLON = ":";

    /**
     * round 参数
     */
    private int roundCur = 1;

    /**
     * 缓存服务发现
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 缓存服务
     */
    private Map<String, Set<String>> targetServiceUrlCache = new HashMap<>();

    /**
     * 是否是第一次运行
     */
    private volatile boolean isFirst = true;

    /**
     * 每隔20秒去获取刷新服务列表
     */
    @Scheduled(fixedRate = 5 * 1000)
    private synchronized void updateTargetUrls() {
        final Map<String, Set<String>> newServiceUrlsCache = new HashMap<>(32);
        discoveryClient.getServices().forEach(serviceName -> {
            //获取并刷新新的服务地址
            final Set<String> newTargetUrls = discoveryClient.getInstances(serviceName)
                    .stream().map(instance ->
                            instance.isSecure() ? SECURED + instance.getHost() + SEMICOLON + instance.getPort() :
                                    NO_SECURED + instance.getHost() + SEMICOLON + instance.getPort()
                    ).collect(Collectors.toSet());
            newServiceUrlsCache.put(serviceName, newTargetUrls);
        });
        if (isFirst) {
            isFirst = false;
        }
        // swap
        this.targetServiceUrlCache = newServiceUrlsCache;
    }

    /**
     * 轮训获取某个微服务的
     *
     * @param serviceId 具体微服务ID
     * @return {@code 微服务地址}
     */
    public String getRoundServiceUrl(String serviceId) {
        // 获取缓存数据
        Set<String> serviceUrl = this.targetServiceUrlCache.get(serviceId);
        // 缓存数据校验
        if (Objects.isNull(serviceUrl) || serviceUrl.size() == 0) {
            if (isFirst) {
                updateTargetUrls();
                serviceUrl = this.targetServiceUrlCache.get(serviceId);
            }
            if (Objects.isNull(serviceUrl) || serviceUrl.size() == 0) {
                log.error("无可用的业务服务");
//                throw BizException.build(ResponseCodeEnum.BUSINESS_EXCEPTION.getMsg(), "业务服务繁忙,稍后重试!");
            }
        }
        // 轮训下一个服务
        roundCur = (++roundCur) % serviceUrl.size();
        return new ArrayList<>(serviceUrl).get(roundCur);
    }

    /**
     * 随机获取某个微服务的
     *
     * @param serviceId 具体微服务ID
     * @return {@code 微服务地址}
     */
    public String getRandomServiceUrl(String serviceId) {
        // 获取缓存数据
        Set<String> serviceUrl = this.targetServiceUrlCache.get(serviceId);
        // 缓存数据校验
        if (Objects.isNull(serviceUrl) || serviceUrl.size() == 0) {
            if (isFirst) {
                updateTargetUrls();
                serviceUrl = this.targetServiceUrlCache.get(serviceId);
            }
            if (serviceUrl.size() == 0) {
                log.error("无可用的业务服务");
//                throw BizException.build(ResponseCodeEnum.BUSINESS_EXCEPTION.getMsg(), "业务服务繁忙,稍后重试!");
            }
        }
        //随机获取一个索引
        final int index = new Random().nextInt(serviceUrl.size());
        return new ArrayList<>(serviceUrl).get(index);
    }

}
