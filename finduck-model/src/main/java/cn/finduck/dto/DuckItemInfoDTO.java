package cn.finduck.dto;

import cn.finduck.model.DuckItemInfoModel;
import lombok.Data;

import java.io.Serializable;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckItemInfoDTO.java
 * @包 路 径： cn.finduck.dto
 * @版权所有：北京数字认证股份有限公司 (C) 2021
 * @类描述:
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/4/25 10:42
 */
@Data
public class DuckItemInfoDTO extends DuckItemInfoModel implements Serializable {

//    @ApiModelProperty(value = "页码",required = true)
    private Integer page;

//    @ApiModelProperty("每页大小")
    private Integer pageSize;
}