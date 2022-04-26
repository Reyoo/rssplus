package cn.finduck.core.infrastructure.config;

import cn.finduck.core.infrastructure.annotation.RestQualifier;
import cn.finduck.core.infrastructure.interceptor.RestTemplateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;

/**
 * @author luocy
 * @description rest Template 配置
 * @create 2021-04-28
 * @since 1.0
 */
@Configuration
public class ClientConfig {

    /**
     * rest template 处理器
     *
     * @return {@link RestTemplateHandler}
     */
    @Bean
    public RestTemplateHandler restTemplateHandler() {
        return new RestTemplateHandler();
    }

    /**
     * rest template
     *
     * @return {@link RestTemplate}
     */
    @Bean
    @RestQualifier
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * rest template 增强
     *
     * @param restTemplateHandler 处理器
     * @return
     */
    @Bean
    public Object restTemplates(@RestQualifier Collection<RestTemplate> restTemplates,
                                @Autowired ClientHttpRequestInterceptor restTemplateHandler) {
        //添加拦截器
        restTemplates.forEach(restTemplate -> restTemplate.setInterceptors(Collections.singletonList(restTemplateHandler)));
        return new Object();
    }

}
