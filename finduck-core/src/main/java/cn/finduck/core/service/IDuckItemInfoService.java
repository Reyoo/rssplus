package cn.finduck.core.service;

import cn.finduck.dto.DuckItemInfoDTO;
import cn.finduck.model.DuckItemInfoModel;
import cn.finduck.model.DuckThemeModel;
import cn.finduck.model.DuckThemeTypeModel;
import com.alibaba.cola.dto.SingleResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

public interface IDuckItemInfoService extends IService<DuckItemInfoModel> {

    Page<DuckItemInfoModel> selectDuckItemByCondition(DuckItemInfoDTO dto, int page, int pageSize);
}
