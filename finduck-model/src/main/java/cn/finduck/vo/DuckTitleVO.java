package cn.finduck.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckTitleVO.java
 * @包 路 径： cn.finduck.vo
 * @版权所有：北京数字认证股份有限公司 (C) 2021
 * @类描述:
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/5/3 16:46
 */
@Data
public class DuckTitleVO implements Serializable {

    String title;

    /**
     * 图标地址
     */
    String picUrl;

    /**
     * 热搜24小时
     */
    String kingKangType = "热搜";



    List<DuckItemInfoVO> data ;

}
