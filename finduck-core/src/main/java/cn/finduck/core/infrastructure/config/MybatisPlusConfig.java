package cn.finduck.core.infrastructure.config;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: MybatisPlusConfig.java
 * @包 路 径： cn.finduck.core.infrastructure.config
 * @类描述:
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/5/2 15:37
 */

import com.baomidou.mybatisplus.annotation.DbType;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: demo
 * @description:
 * @author: 工厂梦
 * @create: 2022-03-15 08:56:26
 **/

@EnableTransactionManagement
@Configuration
@MapperScan("cn.finduck.*.mapper")
public class MybatisPlusConfig {

    /**
     * 新的分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
