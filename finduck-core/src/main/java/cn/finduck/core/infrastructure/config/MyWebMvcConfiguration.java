package cn.finduck.core.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: MyWebMvcConfiguration.java
 * @包 路 径： cn.finduck.core.infrastructure.config
 * @版权所有：北京数字认证股份有限公司 (C) 2021
 * @类描述:
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/5/2 11:32
 */
@Configuration
public class MyWebMvcConfiguration implements WebMvcConfigurer {
    @Value("${finduck.uploadPathImg}")
    private String uploadPathImg;

    //配置本地文件映射到url上
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //重写方法
        //修改tomcat 虚拟映射
        registry.addResourceHandler("/file/**")
                .addResourceLocations("file:" + uploadPathImg);//定义图片存放路径
    }

}