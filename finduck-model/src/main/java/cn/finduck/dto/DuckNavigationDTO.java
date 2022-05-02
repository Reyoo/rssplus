package cn.finduck.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckNavigationDTO.java
 * @包 路 径： cn.finduck.dto
 * @版权所有：北京数字认证股份有限公司 (C) 2021
 * @类描述:
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/5/2 16:56
 */
@Data
public class DuckNavigationDTO implements Serializable {

    Integer page;
    Integer pageSize;
    String typeId;
}
