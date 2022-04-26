package cn.finduck.core.infrastructure.exec;

import cn.finduck.core.infrastructure.annotation.RestQualifier;
import com.alibaba.fastjson.JSON;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author luocy
 * @description HTTP客户端执行器
 * @create 2021-04-28
 * @since 1.0
 */
@Component
public class RestClientExec {

    @Resource
    @RestQualifier
    private RestTemplate restTemplate;

    /**
     * 通过feign客户端调用远程数据
     *
     * @param url                调用地址，可以是http
     * @param returnResponseType 返回响应的类型
     * @param <T> {@link T}
     * @return {@link T}
     */
    public <T> T get(String url, Class<T> returnResponseType) {
        return restTemplate.getForObject(url, returnResponseType);
    }

    /**
     * 通过feign客户端调用远程数据
     *
     * @param url                调用地址，可以是http
     * @param returnResponseType 返回响应的类型
     * @param uriVariables uri请求参数
     * @param <T> {@link T}
     * @return {@link T}
     */
    public <T> T get(String url, Class<T> returnResponseType, Map<String, ?> uriVariables) {
        return restTemplate.getForObject(url, returnResponseType, uriVariables);
    }

    /**
     * 通过feign客户端调用远程数据
     *
     * @param url                调用地址，可以是http
     * @param returnResponseType 返回响应的类型
     * @param request uri请求参数
     * @param <T> {@link T}
     * @return {@link T}
     */
    public <T> T post(String url, Class<T> returnResponseType, Object request) {
        return restTemplate.postForObject(url, JSON.toJSONString(request), returnResponseType);
    }

}
