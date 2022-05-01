package cn.finduck.core.service;

import cn.finduck.model.DuckThemeModel;
import cn.finduck.model.DuckThemeTypeModel;
import cn.finduck.model.DuckTypeModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IDuckThemeService extends IService<DuckThemeModel> {

    Page<DuckThemeModel> pageByCondition(DuckThemeModel dto, int page, int pageSize);
    Page<DuckThemeModel> getPageWithTypeByCondition(DuckThemeTypeModel dto, int page, int pageSize);
}
