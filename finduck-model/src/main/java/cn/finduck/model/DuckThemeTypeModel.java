package cn.finduck.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckThemeTypeModel.java
 * @包 路 径： cn.finduck.model
 * @版权所有：北京数字认证股份有限公司 (C) 2021
 * @类描述:
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/5/1 08:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("duck_theme_type")
@Builder
public class DuckThemeTypeModel  implements Serializable {

    @ApiModelProperty(value = "主题类型关联ID")
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    @ApiModelProperty(value = "主题ID")
    @TableField("theme_id")
    Integer themeId;
    @ApiModelProperty(value = "类型ID")
    @TableField("type_id")
    Integer typeId;

}
