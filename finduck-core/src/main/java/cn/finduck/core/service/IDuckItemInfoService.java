package cn.finduck.core.service;

import cn.finduck.dto.DuckItemInfoDTO;
import cn.finduck.model.DuckItemInfoModel;
import com.alibaba.cola.dto.SingleResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

public interface IDuckItemInfoService extends IService<DuckItemInfoModel> {
    SingleResponse<PageInfo<DuckItemInfoModel>> selectDuckItemByCondition(DuckItemInfoDTO dto);
}
