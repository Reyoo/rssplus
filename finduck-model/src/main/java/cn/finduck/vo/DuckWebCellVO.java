package cn.finduck.vo;


import lombok.Data;

import java.util.List;
import java.util.Map;

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
public class DuckWebCellVO {

    String typeName;
    Map<String,List<DuckItemInfoVO>> data;
    Long total;
}
