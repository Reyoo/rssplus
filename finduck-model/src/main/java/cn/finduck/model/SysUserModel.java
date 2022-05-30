package cn.finduck.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
import java.time.LocalDate;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: SysUserModel.java
 * @包 路 径： cn.finduck.model
 * @版权所有：北京数字认证股份有限公司 (C) 2021
 * @类描述:
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/5/1 16:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
@Builder
public class SysUserModel  implements Serializable {

    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty(value = "用户ID 主键")
    Integer userId;

    @ApiModelProperty(value = "用户名称")
    @TableField(value = "user_name")
    String userName;

    @ApiModelProperty(value = "用户手机号")
    @TableField(value = "user_mobile")
    String userMobile;

    @ApiModelProperty(value = "用户密码")
    @TableField(value = "user_password")
    String userPassword;

    @ApiModelProperty(value = "用户密码明文")
    @TableField(value = "user_pwd_str")
    String userPwdStr;

    @ApiModelProperty(value = "类型名称")
    @TableField(value = "user_status")
    Boolean userStatus;

    @ApiModelProperty(value = "类型名称")
    @TableField(value = "allowremove")
    Boolean allowRemove;

    @ApiModelProperty(value = "类型名称")
    @TableField(value = "user_lastlogin_time")
    LocalDate userLastloginTime;

    @ApiModelProperty(value = "类型名称")
    @TableField(value = "create_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDate createTime;


}
