package cn.finduck.core.service.impl;

import cn.finduck.core.mapper.DuckItemInfoModelMapper;
import cn.finduck.core.mapper.DuckThemeModelMapper;
import cn.finduck.core.service.IDuckItemInfoService;
import cn.finduck.core.service.IDuckThemeService;
import cn.finduck.dto.DuckItemInfoDTO;
import cn.finduck.model.DuckItemInfoModel;
import cn.finduck.model.DuckThemeModel;
import cn.hutool.core.util.StrUtil;
import com.alibaba.cola.dto.SingleResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckItemInfoServiceImpl.java
 * @包 路 径： cn.finduck.core.service.impl
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述: 身份证号校验
 * @版本: V1.0 @创建人：SunQi
 * @创建时间：2022/4/24 11:19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class DuckItemInfoServiceImpl extends ServiceImpl<DuckItemInfoModelMapper, DuckItemInfoModel> implements IDuckItemInfoService {

    private final DuckItemInfoModelMapper duckItemInfoModelMapper;

    @Override
    public SingleResponse<PageInfo<DuckItemInfoModel>> selectDuckItemByCondition(DuckItemInfoDTO dto) {
        QueryWrapper<DuckItemInfoModel> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(DuckItemInfoModel::getBanStatus, 1);
        if (StrUtil.isNotBlank(dto.getMsgThemeName())) {
            queryWrapper.lambda().eq(DuckItemInfoModel::getMsgThemeName, dto.getMsgThemeName());
        }
        if (StrUtil.isNotBlank(dto.getMsgThemeDesc())) {
            queryWrapper.lambda().eq(DuckItemInfoModel::getMsgThemeDesc, dto.getMsgThemeDesc());
        }
        List<DuckItemInfoModel> duckItemInfoModels = duckItemInfoModelMapper.selectList(queryWrapper);
        PageInfo<DuckItemInfoModel> duckItemInfoModelPageInfo = new PageInfo<>(duckItemInfoModels);
        return SingleResponse.of(duckItemInfoModelPageInfo);
    }
}
