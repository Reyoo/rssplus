package cn.finduck.core.service;

import cn.finduck.model.DuckKingkangModel;
import cn.finduck.model.DuckThemeTypeModel;
import cn.finduck.model.DuckWebCellModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IDuckWebCellService extends IService<DuckWebCellModel> {

    Page<DuckWebCellModel> getPageWithTypeByCondition(DuckWebCellModel dto, int page, int pageSize);
}
