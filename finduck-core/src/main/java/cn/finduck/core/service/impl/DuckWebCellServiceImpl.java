package cn.finduck.core.service.impl;

import cn.finduck.constant.FinduckConstants;
import cn.finduck.core.mapper.*;
import cn.finduck.core.service.IDuckThemeTypeService;
import cn.finduck.core.service.IDuckWebCellService;
import cn.finduck.dto.DuckNavigationDTO;
import cn.finduck.model.*;
import cn.finduck.vo.DuckItemInfoVO;
import cn.finduck.vo.DuckTitleVO;
import cn.finduck.vo.DuckWebCellVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private final DuckTypeModelMapper duckTypeModelMapper;

    private final DuckThemeTypeModelMapper duckThemeTypeModelMapper;
    private final DuckThemeModelMapper duckThemeModelMapper;

    private final DuckItemInfoModelMapper duckItemInfoModelMapper;

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

    @Override
    public List<DuckWebCellVO> pageWebCell(int pageInfo, int pageSize) {
        Page<DuckTypeModel> page = new Page<>(pageInfo, pageSize);
        QueryWrapper<DuckTypeModel> queryWrapper = new QueryWrapper<DuckTypeModel>();
        queryWrapper.lambda().eq(DuckTypeModel::getTypeStatus,FinduckConstants.ONUSE);
        Page<DuckTypeModel> page1 = duckTypeModelMapper.selectPage(page, queryWrapper);
        List<DuckTypeModel> records = page1.getRecords();
        ArrayList<DuckWebCellVO> duckWebCellVOS = new ArrayList<>();

        records.stream().forEach(t -> {
            DuckWebCellVO duckWebCellVO = new DuckWebCellVO();
            duckWebCellVO.setTypeName(t.getTypeName());
            HashMap<String, List<DuckItemInfoVO>> stringListHashMap = new HashMap<>();

            QueryWrapper<DuckThemeTypeModel> themeTypeQueryWrapper = new QueryWrapper<DuckThemeTypeModel>();
            themeTypeQueryWrapper.lambda().eq(DuckThemeTypeModel::getTypeId, t.getId());
            List<DuckThemeTypeModel> duckThemeTypeModels = duckThemeTypeModelMapper.selectList(themeTypeQueryWrapper);
            List<Integer> themeIds = duckThemeTypeModels.stream().map(s -> s.getThemeId()).collect(Collectors.toList());
            ArrayList<DuckTitleVO> duckTitleVOS = new ArrayList<>();
            getDuckThemeWithTypeId(duckWebCellVO, duckTitleVOS, themeIds);
            duckWebCellVOS.add(duckWebCellVO);
        });
        return duckWebCellVOS;
    }


    @Override
    public DuckWebCellVO pageByItemId(DuckNavigationDTO dto) {
        if(dto!=null){
            if(dto.getPage()==null); dto.setPage(1);
            if(dto.getPageSize()==null); dto.setPageSize(8);
        }
        Page<DuckThemeTypeModel> page = new Page<>(dto.getPage(), dto.getPageSize());

        DuckWebCellVO duckWebCellVO = new DuckWebCellVO();
        ArrayList<DuckWebCellVO> duckWebCellVOS = new ArrayList<>();

        /**
         * 给类型赋名称
         */
        QueryWrapper<DuckTypeModel> queryWrapper = new QueryWrapper<DuckTypeModel>();
        queryWrapper.lambda().eq(DuckTypeModel::getId,dto.getTypeId());
        DuckTypeModel duckTypeModel = duckTypeModelMapper.selectOne(queryWrapper);
        duckWebCellVO.setTypeName(duckTypeModel.getTypeName());


        QueryWrapper<DuckThemeTypeModel> themeTypeQueryWrapper = new QueryWrapper<DuckThemeTypeModel>();
        themeTypeQueryWrapper.lambda().eq(DuckThemeTypeModel::getTypeId, dto.getTypeId());
        Page<DuckThemeTypeModel> duckThemeTypeModelPage = duckThemeTypeModelMapper.selectPage(page, themeTypeQueryWrapper);
        List<Integer> themeIds = duckThemeTypeModelPage.getRecords().stream().map(s -> s.getThemeId()).collect(Collectors.toList());

        ArrayList<DuckTitleVO> duckTitleVOS = new ArrayList<>();
        getDuckThemeWithTypeId(duckWebCellVO, duckTitleVOS, themeIds);
        duckWebCellVO.setTotal(duckThemeTypeModelPage.getTotal());
        return duckWebCellVO;
    }

    private void getDuckThemeWithTypeId(DuckWebCellVO duckWebCellVO,  ArrayList<DuckTitleVO> duckTitleVOS, List<Integer> themeIds) {
        themeIds.forEach(  b ->{
            DuckThemeModel duckThemeModel = duckThemeModelMapper.selectById(b);
            Page<DuckItemInfoModel> duckItemInfoModelPage = new Page<>(1,10);
            QueryWrapper<DuckItemInfoModel> duckItemInfoModelQueryWrapper = new QueryWrapper<>();
            duckItemInfoModelQueryWrapper.lambda().orderByDesc(DuckItemInfoModel::getPubDate);
            duckItemInfoModelQueryWrapper.lambda().eq(DuckItemInfoModel::getMsgThemeId,duckThemeModel.getId());
            Page<DuckItemInfoModel> duckItemInfoModelPage1 = duckItemInfoModelMapper.selectPage(duckItemInfoModelPage, duckItemInfoModelQueryWrapper);
            List<DuckItemInfoModel> duckItemInfoModels = duckItemInfoModelPage1.getRecords();

            List<DuckItemInfoVO> projectResponseList = duckItemInfoModels.stream().map((entity) -> {
                DuckItemInfoVO vo = new DuckItemInfoVO();
                BeanUtils.copyProperties(entity,vo);
                return vo;
            }).collect(Collectors.toList());
            DuckTitleVO duckTitleVO = new DuckTitleVO();
            duckTitleVO.setTitle(duckThemeModel.getThemeDescription());
            duckTitleVO.setData(projectResponseList);
            duckTitleVOS.add(duckTitleVO);

        });
        duckWebCellVO.setInfos(duckTitleVOS);
    }
}
