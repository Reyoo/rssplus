package cn.finduck.core.service.impl;

import cn.finduck.constant.FinduckConstants;
import cn.finduck.core.mapper.DuckKingkangModelMapper;
import cn.finduck.core.mapper.DuckThemeTypeModelMapper;
import cn.finduck.core.mapper.DuckWebCellModelMapper;
import cn.finduck.core.service.IDuckThemeTypeService;
import cn.finduck.core.service.IDuckWebCellService;
import cn.finduck.model.DuckKingkangModel;
import cn.finduck.model.DuckThemeTypeModel;
import cn.finduck.model.DuckWebCellModel;
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
public class DuckWebCellServiceImpl extends ServiceImpl<DuckWebCellModelMapper, DuckWebCellModel> implements IDuckWebCellService {


    private final DuckWebCellModelMapper duckWebCellModelMapper;
    @Override
    public Page<DuckWebCellModel> getPageWithTypeByCondition(DuckWebCellModel dto, int page, int pageSize) {
        Page<DuckWebCellModel> pageInfo = new Page<>(page, pageSize);
        QueryWrapper<DuckWebCellModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(DuckWebCellModel::getCellOrder);
        queryWrapper.lambda().eq(DuckWebCellModel::getCellStatus, FinduckConstants.ONUSE);
        /**
         * todo: 补充根据类型返回的结果 这个接口逻辑是错误的  返回的数据格式应当是 ：  [“综合”:  List[]]]
         */
        return duckWebCellModelMapper.selectPage(pageInfo, queryWrapper);

    }

}
