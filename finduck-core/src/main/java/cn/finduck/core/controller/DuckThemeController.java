package cn.finduck.core.controller;

import cn.finduck.core.service.IDuckItemInfoService;
import cn.finduck.core.service.IDuckThemeService;
import cn.finduck.core.service.IDuckThemeTypeService;
import cn.finduck.core.service.IDuckTypeService;
import cn.finduck.dto.DuckItemInfoDTO;
import cn.finduck.dto.DuckThemeDTO;
import cn.finduck.dto.DuckThemeTypeDTO;
import cn.finduck.dto.DuckTypeDTO;
import cn.finduck.model.DuckItemInfoModel;
import cn.finduck.model.DuckThemeModel;
import cn.finduck.model.DuckThemeTypeModel;
import cn.finduck.model.DuckTypeModel;
import com.alibaba.cola.dto.SingleResponse;
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
@Api(tags = "管理员-新增主题")
@RestController
@RequestMapping("/theme")
public class DuckThemeController {

    private final IDuckThemeService iDuckThemeService;
    private final IDuckItemInfoService iDuckItemInfoService;
    private  final IDuckThemeTypeService iDuckThemeTypeService;


    @ApiOperation("新增主题-关联类型")
    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    public SingleResponse<Boolean> addOrUpdate(@RequestBody DuckItemInfoDTO dto) {
        Assert.notNull(dto.getTitle(),"标题不能为空");
        Assert.notNull(dto.getLink(),"链接不能为空");
        DuckItemInfoModel duckItemInfoModel = new DuckItemInfoModel();
        BeanUtils.copyProperties(dto, duckItemInfoModel);
        boolean b = iDuckItemInfoService.saveOrUpdate(duckItemInfoModel);
        if(b){
            DuckThemeTypeModel duckThemeTypeModel =  DuckThemeTypeModel.builder().themeId(duckItemInfoModel.getMsgThemeId()).typeId(dto.getTypeId()).build();
            iDuckThemeTypeService.save(duckThemeTypeModel);
        }
        return SingleResponse.of(b);
    }


    @ApiOperation("查询主题")
    @RequestMapping(value = "/byConditions", method = RequestMethod.POST)
    public SingleResponse<Page<DuckThemeModel>> byConditions(@RequestBody DuckItemInfoDTO dto) {
        DuckThemeModel duckThemeModel = new DuckThemeModel();
        BeanUtils.copyProperties(dto,duckThemeModel);
        Page<DuckThemeModel> duckThemeModelPage = iDuckThemeService.pageByCondition(duckThemeModel, dto.getPage() == null ? 0 : dto.getPage(), dto.getPageSize() == null ? 10 : dto.getPageSize());
        return SingleResponse.of(duckThemeModelPage);
    }

    @ApiOperation("根据类型查询主题")
    @RequestMapping(value = "/getThemeByType", method = RequestMethod.POST)
    public SingleResponse<Page<DuckThemeModel>> byConditions(@RequestBody DuckThemeTypeDTO dto) {
        DuckThemeTypeModel duckThemeTypeModel = new DuckThemeTypeModel();
        BeanUtils.copyProperties(dto,duckThemeTypeModel);
        Page<DuckThemeModel> duckThemeModelPage = iDuckThemeService.getPageWithTypeByCondition(duckThemeTypeModel, dto.getPage() == null ? 0 : dto.getPage(), dto.getPageSize() == null ? 10 : dto.getPageSize());
        return SingleResponse.of(duckThemeModelPage);
    }


    @ApiOperation("删除主题")
    @RequestMapping(value = "/delTheme", method = RequestMethod.DELETE)
    public SingleResponse<Boolean> byConditions(@RequestBody DuckThemeDTO dto) {
        Assert.notNull(dto.getId(),"主键不能为空");
        boolean b = iDuckThemeService.removeById(dto.getId());
        return SingleResponse.of(b);
    }

}
