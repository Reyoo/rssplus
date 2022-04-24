package cn.finduck.core.scheduled;

import cn.finduck.core.client.WebFeedConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: RssScheduleTask.java
 * @包 路 径： cn.finduck.core.scheduled
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述: 身份证号校验
 * @版本: V1.0 @创建人：SunQi
 * @创建时间：2022/4/24 17:29
 */

@Configuration    //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling  // 2.开启定时任务
public class RssScheduleTask {

    @Autowired
    WebFeedConsumer webFeedConsumer;
    //3.添加定时任务
    @Scheduled(cron = "0 38 17 * * ? ")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        webFeedConsumer.saveThemeThenGetUrlPath();
        System.err.println("执行完定时任务时间: " + LocalDateTime.now());
    }
}