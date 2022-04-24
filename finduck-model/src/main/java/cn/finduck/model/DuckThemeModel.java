package cn.finduck.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckThemeModel.java
 * @包 路 径： cn.finduck.model
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述: 身份证号校验
 * @版本: V1.0 @创建人：SunQi
 * @创建时间：2022/4/24 10:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("duck_theme")
public class DuckThemeModel extends Model<DuckThemeModel> {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    Integer id;
    @TableField("theme_name")
    String themeName;
    @TableField("theme_url_prefix")
    String themeUrlPrefix;
    @TableField("theme_description")
    String themeDescription;
    /**
     * 启禁用标识 true 禁用 false 启用
     */
    @TableField("theme_deleted")
    Boolean themeDeleted;
}
