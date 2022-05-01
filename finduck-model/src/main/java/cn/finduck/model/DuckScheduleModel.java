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
public class DuckScheduleModel extends Model<DuckScheduleModel> {

    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    /**
     * cron表达式
     */
    @TableField("cron_info")
    @ApiModelProperty(value = "cron表达式")
    String cronInfo;
    /**
     * 主题ID
     */
    @TableField("theme_id")
    @ApiModelProperty(value = "主题ID")
    Integer themeId;

    @TableField("schedule_status")
    @ApiModelProperty(value = "启禁用状态")
    Boolean scheduleStatus;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间")
    LocalDateTime createTime;

}
