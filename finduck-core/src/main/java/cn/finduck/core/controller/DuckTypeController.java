package cn.finduck.core.controller;

import cn.finduck.core.service.IDuckItemInfoService;
import cn.finduck.core.service.IDuckThemeService;
import cn.finduck.core.service.IDuckThemeTypeService;
import cn.finduck.core.service.IDuckTypeService;
import cn.finduck.dto.DuckItemInfoDTO;
import cn.finduck.dto.DuckThemeDTO;
import cn.finduck.dto.DuckTypeDTO;
import cn.finduck.model.DuckItemInfoModel;
import cn.finduck.model.DuckThemeTypeModel;
import cn.finduck.model.DuckTypeModel;
import com.alibaba.cola.dto.SingleResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckItemInfoController.java
 * @包 路 径： cn.finduck.core.controller
 * @版权所有：北京数字认证股份有限公司 (C) 2021
 * @类描述:
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/4/25 18:22
 */

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Api(tags = {"消息类型主题"})
@RestController
@RequestMapping("/type")


public class DuckTypeController {


    private final IDuckTypeService duckTypeService;
    private final IDuckThemeTypeService duckThemeTypeService;

    @ApiOperation("新增或修改消息类型")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public SingleResponse<Boolean> saveOrUpdateDuckType(@RequestBody DuckTypeModel dto) {
        Assert.isNull(dto.getTypeName(), "类型名词不能为空");
        if (dto.getTypeStatus() == null) dto.setTypeStatus(Boolean.TRUE);
        boolean save = duckTypeService.saveOrUpdate(dto);
        return SingleResponse.of(save);
    }




    @ApiOperation("查询类型")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public SingleResponse<Page<DuckTypeModel>> getPageByConditions(@RequestBody DuckTypeDTO dto) {
        DuckTypeModel duckTypeModel = new DuckTypeModel();
        BeanUtils.copyProperties(dto,duckTypeModel);
        Page<DuckTypeModel> duckTypeModelPage = duckTypeService.pageByCondition(duckTypeModel, dto.getPage() == null ? 0 : dto.getPage(), dto.getPageSize() == null ? 10 : dto.getPageSize());
        return SingleResponse.of(duckTypeModelPage);
    }


    @ApiOperation("删除类型")
    @RequestMapping(value = "/delType", method = RequestMethod.DELETE)
    public SingleResponse<Boolean> byConditions(@RequestBody DuckTypeDTO dto) {
        Assert.notNull(dto.getId(),"主键不能为空");
        QueryWrapper<DuckThemeTypeModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DuckThemeTypeModel::getTypeId,dto.getId());
        long count = duckThemeTypeService.count(queryWrapper);
        if(count>0) return SingleResponse.buildFailure("50000","删除失败先删除关联");
        boolean b = duckTypeService.removeById(dto.getId());
        return SingleResponse.of(b);
    }



}
