package cn.finduck.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckTypeModel.java
 * @包 路 径： cn.finduck.model
 * @版权所有：北京数字认证股份有限公司 (C) 2021
 * @类描述:
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/5/1 08:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("duck_type")
@Builder
public class DuckTypeModel    implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    Integer id;
    @ApiModelProperty(value = "类型名称")
    @TableField(value = "type_name")
    String typeName;
    @ApiModelProperty(value = "类型描述")
    @TableField(value = "type_desc")
    String typeDesc;
    @ApiModelProperty(value = "类型状态")
    @TableField(value = "type_status")
    Boolean typeStatus;
    @TableField(value = "router")
    String routeStr;
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "createDate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDate createDate;


}
