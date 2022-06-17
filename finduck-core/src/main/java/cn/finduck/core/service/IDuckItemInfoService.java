package cn.finduck.core.service;

import cn.finduck.dto.DuckItemInfoDTO;
import cn.finduck.model.DuckItemInfoModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IDuckItemInfoService extends IService<DuckItemInfoModel> {

    Page<DuckItemInfoModel> selectDuckItemByCondition(DuckItemInfoDTO dto, int page, int pageSize);
}
