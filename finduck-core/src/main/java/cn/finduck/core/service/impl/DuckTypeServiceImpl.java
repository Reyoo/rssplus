package cn.finduck.core.service.impl;

import cn.finduck.constant.FinduckConstants;
import cn.finduck.core.mapper.DuckThemeTypeModelMapper;
import cn.finduck.core.mapper.DuckTypeModelMapper;
import cn.finduck.core.service.IDuckThemeTypeService;
import cn.finduck.core.service.IDuckTypeService;
import cn.finduck.model.DuckThemeTypeModel;
import cn.finduck.model.DuckTypeModel;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckThemeServiceImpl.java
 * @包 路 径： cn.finduck.core.service.impl
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述: 身份证号校验
 * @版本: V1.0 @创建人：SunQi
 * @创建时间：2022/4/24 11:13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class DuckTypeServiceImpl extends ServiceImpl<DuckTypeModelMapper, DuckTypeModel> implements IDuckTypeService {

    private final DuckTypeModelMapper duckTypeModelMapper;

    @Override
    public Page<DuckTypeModel> pageByCondition(DuckTypeModel dto,int page,int pageSize){
        Page<DuckTypeModel> pageInfo = new Page<>(page,pageSize);
        //这里通过调用PageHelper的静态方法，设置了PageHelper的起始页以及每页的数量
        QueryWrapper<DuckTypeModel> queryWrapper = new QueryWrapper<>();
        if(dto!=null){
            if(StrUtil.isNotBlank(dto.getTypeName())) queryWrapper.lambda().eq(DuckTypeModel::getTypeName,dto.getTypeName());
            if(StrUtil.isNotBlank(dto.getTypeDesc())) queryWrapper.lambda().eq(DuckTypeModel::getTypeDesc,dto.getTypeDesc());
            if(ObjectUtil.isNotNull(dto.getId())) queryWrapper.lambda().eq(DuckTypeModel::getId,dto.getId());
        }
//        queryWrapper.lambda().eq(DuckTypeModel::getTypeStatus, FinduckConstants.ONUSE);
        queryWrapper.lambda().orderByDesc(DuckTypeModel::getTypeStatus);
        queryWrapper.lambda().orderByDesc(DuckTypeModel::getCreateDate);
        return  duckTypeModelMapper.selectPage(pageInfo, queryWrapper);

    }
}
