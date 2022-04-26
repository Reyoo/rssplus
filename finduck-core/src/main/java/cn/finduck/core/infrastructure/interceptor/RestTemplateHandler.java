package cn.finduck.core.infrastructure.interceptor;


import cn.finduck.core.infrastructure.discovery.CacheDiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

/**
 * @author luocy
 * @description RestTemplate处理器
 * @create 2021-04-28
 * @since 1.0
 */
public class RestTemplateHandler implements ClientHttpRequestInterceptor {

    /**
     * 缓存服务发现信息
     */
    @Resource
    private CacheDiscoveryClient cacheDiscoveryClient;

    @Autowired
    @Qualifier
    private RestTemplate restTemplate;

    /**
     * 处理请求
     *
     * @param request   请求
     * @param body      请求体
     * @param execution 执行器
     * @return {@link RestTemplateClientHttpResponse}
     * @throws IOException
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        final URI requestUri = request.getURI();
        final String path = requestUri.getPath();
        // real address
        String actualUrl;
        // handler
        if (!requestUri.toString().startsWith(CacheDiscoveryClient.SECURED) && !requestUri.toString().startsWith(CacheDiscoveryClient.NO_SECURED)) {
            final String[] parts = StringUtils.delimitedListToStringArray(path.substring(1), "/");
            if (parts.length == 1) {
                //最终服务地址
                actualUrl = cacheDiscoveryClient.getRoundServiceUrl(path.substring(1));
            } else {
                String serviceName = parts[0];
                int index = Math.min(path.substring(1).indexOf("/") + 1, path.substring(1).length());
                String uri = path.substring(index);
                actualUrl = cacheDiscoveryClient.getRoundServiceUrl(serviceName) + uri;
            }
            SimpleClientHttpRequestFactory clientFactory = new SimpleClientHttpRequestFactory();
            final ClientHttpRequest requestNew = clientFactory
                    .createRequest(URI.create(actualUrl),
                            Objects.isNull(request.getMethod()) ? HttpMethod.POST : request.getMethod());
            requestNew.getHeaders().addAll(request.getHeaders());
            requestNew.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return execution.execute(requestNew, body);
        } else {
            // http
            SimpleClientHttpRequestFactory clientFactory = new SimpleClientHttpRequestFactory();
            final ClientHttpRequest requestNew = clientFactory
                    .createRequest(requestUri,
                            Objects.isNull(request.getMethod()) ? HttpMethod.POST : request.getMethod());
            requestNew.getHeaders().addAll(request.getHeaders());
            requestNew.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return execution.execute(requestNew, body);
        }
    }
}
