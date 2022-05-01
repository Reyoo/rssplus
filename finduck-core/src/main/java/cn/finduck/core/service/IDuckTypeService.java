package cn.finduck.core.service;

import cn.finduck.model.DuckThemeTypeModel;
import cn.finduck.model.DuckTypeModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IDuckTypeService extends IService<DuckTypeModel> {


     Page<DuckTypeModel> pageByCondition(DuckTypeModel dto, int page, int pageSize);

}
