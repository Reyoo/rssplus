package cn.finduck.finduckweb.infrastructure.config;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: MultipartSupportConfig.java
 * @包 路 径： cn.finduck.finduckweb.infrastructure.config
 * @版权所有：北京数字认证股份有限公司 (C) 2021
 * @类描述:
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/5/3 14:05
 */

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import feign.codec.Encoder;
import org.springframework.context.annotation.Configuration;

/**
 * FileName: MultipartSupportConfig
 * Author:   SixJR.
 * Date:     2022/3/2 19:56:43
 * Description: 解决Feign在组装MultipartFile[]的时候出现的问题
 * History:
 * <author>          <time>          <version>          <desc>
 */

@Configuration
public class MultipartSupportConfig {
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public Encoder feignFormEncoder() {
        return new SpringMultipartEncoder(new SpringEncoder(messageConverters));
    }
}

