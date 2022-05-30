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
 * @文件名称: DuckUserCellModel.java
 * @包 路 径： cn.finduck.model
 * @版权所有：北京数字认证股份有限公司 (C) 2021
 * @类描述:
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/5/1 16:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("duck_user_cell")
@Builder
public class DuckUserCellModel  implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    Integer id;
    @ApiModelProperty(value = "单元ID ")
    @TableField(value = "cell_id")
    Integer cellId;

    @ApiModelProperty(value = "用户ID")
    @TableField(value = "user_id")
    Integer userId;

}
