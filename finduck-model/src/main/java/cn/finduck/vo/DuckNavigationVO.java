package cn.finduck.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckNavigationVO.java
 * @包 路 径： cn.finduck.vo
 * @类描述:
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/5/2 16:27
 */
@Data
public class DuckNavigationVO implements Serializable {

    Integer id;
    @ApiModelProperty(value = "类型名称")
    String typeName;
    @ApiModelProperty(value = "类型描述")
    String typeDesc;

    @ApiModelProperty(value = "路由字段")
    String routeStr;

}
