package cn.finduck.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: ItemInfoModel.java
 * @包 路 径： cn.finduck.model
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述: 身份证号校验
 * @版本: V1.0 @创建人：SunQi
 * @创建时间：2022/4/22 14:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("duck_itemInfo")
//@Accessors(chain = true)
@Builder
public class DuckItemInfoModel extends Model<DuckItemInfoModel> {

    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    /**
     * 标题
     */
    @TableField("title")
    @ApiModelProperty(value = "内容标题")
    String title;
    /**
     * 内容
     */
    @TableField("description")
    @ApiModelProperty(value = "内容描述")
    String description;
//    String guid;
    /**
     * 网站实际链接
     */
    @TableField("link")
    @ApiModelProperty(value = "内容链接")
    String link;
    /**
     * 推送日期
     */
    @TableField("pubDate")
    @ApiModelProperty(value = "推送时间")
    LocalDateTime pubDate;
    /**
     * 作者
     */
    @TableField("author")
    @ApiModelProperty(value = "原文作者")
    String author;

    /**
     * 内容主题ID
     */
    @TableField("msg_theme_name")
    @ApiModelProperty(value = "内容主题名称")
    String msgThemeName;

    /**
     * 内容主题详细
     */
    @TableField("msg_theme_desc")
    @ApiModelProperty(value = "内容主题详细")
    String  msgThemeDesc;

    @TableField("msg_theme_id")
    @ApiModelProperty(value = "主题ID")
    Integer msgThemeId;

    @TableField("banStatus")
    @ApiModelProperty(value = "启禁用状态")
    Boolean banStatus;
}
