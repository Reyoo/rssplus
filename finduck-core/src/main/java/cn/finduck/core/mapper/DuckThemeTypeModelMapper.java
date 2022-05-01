package cn.finduck.core.mapper;

import cn.finduck.model.DuckItemInfoModel;
import cn.finduck.model.DuckThemeModel;
import cn.finduck.model.DuckThemeTypeModel;
import cn.finduck.model.DuckTypeModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DuckThemeTypeModelMapper extends BaseMapper<DuckThemeTypeModel> {


    Page<DuckThemeModel> getPageWithTypeByCondition(DuckThemeTypeModel dto, Page page);

}
