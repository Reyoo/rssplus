package cn.finduck.core.infrastructure.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luocy
 * @description 请求响应传输实体
 * @create 2021-04-28
 * @since 1.0
 */
@Data
public class RestTemplateRespDTO implements Serializable {

    private static final long serialVersionUID = 1902488875071106705L;

    /**
     * 响应码 500200 成功其他失败，失败需要业务系统回执错误信息
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;
}
