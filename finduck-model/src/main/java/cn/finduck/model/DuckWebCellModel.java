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

import java.time.LocalDateTime;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckWebCell.java
 * @包 路 径： cn.finduck.model
 * @版权所有：北京数字认证股份有限公司 (C) 2021
 * @类描述:
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/5/1 16:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("duck_web_cell")
@Builder
public class DuckWebCellModel {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    Integer id;
    @ApiModelProperty(value = "单元排序")
    @TableField(value = "cell_name")
    String cellName;

    @ApiModelProperty(value = "单元排序")
    @TableField(value = "cell_order")
    Integer cellOrder;

    @ApiModelProperty(value = "单元类型ID")
    @TableField(value = "cell_type_id")
    Integer cellTypeId;
    @ApiModelProperty(value = "单元主题ID")
    @TableField(value = "cell_theme_id")
    Integer cellThemeId;

    @ApiModelProperty(value = "单元显示内容数量")
    @TableField(value = "cell_num")
    Integer cellNum;

    @ApiModelProperty(value = "单元启用状态1启用、2禁用")
    @TableField(value = "cell_status")
    Boolean cellStatus;

    @ApiModelProperty(value = "单元图片")
    @TableField(value = "cell_pic")
    String cellPic;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    LocalDateTime createTime;
}
