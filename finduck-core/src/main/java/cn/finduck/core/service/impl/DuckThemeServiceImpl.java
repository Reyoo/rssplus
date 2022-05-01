package cn.finduck.core.service.impl;

import cn.finduck.core.mapper.DuckThemeModelMapper;
import cn.finduck.core.mapper.DuckThemeTypeModelMapper;
import cn.finduck.core.service.IDuckThemeService;
import cn.finduck.model.DuckThemeModel;
import cn.finduck.model.DuckThemeTypeModel;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckThemeServiceImpl.java
 * @包 路 径： cn.finduck.core.service.impl
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @版本: V1.0 @创建人：SunQi
 * @创建时间：2022/4/24 11:13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class DuckThemeServiceImpl extends ServiceImpl<DuckThemeModelMapper, DuckThemeModel> implements IDuckThemeService {

    private final DuckThemeModelMapper duckThemeModelMapper;

    private final DuckThemeTypeModelMapper duckThemeTypeModelMapper;

    @Override
    public Page<DuckThemeModel> pageByCondition(DuckThemeModel dto, int page, int pageSize) {
        Page<DuckThemeModel> pageInfo = new Page<>(page,pageSize);
        //这里通过调用PageHelper的静态方法，设置了PageHelper的起始页以及每页的数量
        QueryWrapper<DuckThemeModel> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isNotBlank(dto.getThemeName())) queryWrapper.lambda().eq(DuckThemeModel::getThemeName,dto.getThemeName());
        if(StrUtil.isNotBlank(dto.getThemeDescription())) queryWrapper.lambda().eq(DuckThemeModel::getThemeDescription,dto.getThemeDescription());
        if(StrUtil.isNotBlank(dto.getThemeUrlPrefix())) queryWrapper.lambda().eq(DuckThemeModel::getThemeUrlPrefix,dto.getThemeUrlPrefix());
        queryWrapper.lambda().orderByDesc(DuckThemeModel::getThemeDeleted);
        queryWrapper.lambda().orderByDesc(DuckThemeModel::getCreateDate);
        return  duckThemeModelMapper.selectPage(pageInfo, queryWrapper);
    }

    @Override
    public Page<DuckThemeModel> getPageWithTypeByCondition(DuckThemeTypeModel dto, int page, int pageSize) {
        Page<DuckThemeTypeModel> pageInfo = new Page<>(page,pageSize);
        Page<DuckThemeModel> pageWithTypeByCondition = duckThemeTypeModelMapper.getPageWithTypeByCondition(dto, pageInfo);
        return pageWithTypeByCondition;
    }


}
