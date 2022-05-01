package cn.finduck.core.service.impl;

import cn.finduck.core.mapper.DuckKingkangModelMapper;
import cn.finduck.core.service.IDuckKingkangService;
import cn.finduck.model.DuckKingkangModel;
import cn.finduck.model.DuckScheduleModel;
import cn.finduck.model.DuckThemeModel;
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
 * @类描述: 身份证号校验
 * @版本: V1.0 @创建人：SunQi
 * @创建时间：2022/4/24 11:13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class DuckKingkangServiceImpl extends ServiceImpl<DuckKingkangModelMapper, DuckKingkangModel> implements IDuckKingkangService {

}
