package cn.finduck.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
@TableName("duck_kingkang")
//@Accessors(chain = true)
@Builder
public class DuckKingkangModel extends Model<DuckKingkangModel> implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    /**
     * cron表达式
     */
    @TableField("kingkang_name")
    @ApiModelProperty(value = "金刚位名称")
    String kingkangName;
    /**
     * 主题ID
     */
    @TableField("kingkang_url")
    @ApiModelProperty(value = "金刚位Url")
    Integer kingkangUrl;

    @TableField("kingkang_pic")
    @ApiModelProperty(value = "金刚图片")
    String  kingkangPic;

    @TableField("kingkang_order")
    @ApiModelProperty(value = "金刚位置")
    Integer kingkangOrder;

    @TableField("kingkang_status")
    @ApiModelProperty(value = "金刚位启用状态1启用、0禁用")
    Boolean kingkangStatus;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime createTime;

}
