package cn.finduck.core.service.impl;

import cn.finduck.core.mapper.DuckItemInfoModelMapper;
import cn.finduck.core.mapper.DuckThemeModelMapper;
import cn.finduck.core.mapper.DuckThemeTypeModelMapper;
import cn.finduck.core.service.IDuckThemeService;
import cn.finduck.core.service.IDuckThemeTypeService;
import cn.finduck.model.DuckThemeModel;
import cn.finduck.model.DuckThemeTypeModel;
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
 * @类描述: 身份证号校验
 * @版本: V1.0 @创建人：SunQi
 * @创建时间：2022/4/24 11:13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class DuckThemeTypeServiceImpl extends ServiceImpl<DuckThemeTypeModelMapper, DuckThemeTypeModel> implements IDuckThemeTypeService {



}
