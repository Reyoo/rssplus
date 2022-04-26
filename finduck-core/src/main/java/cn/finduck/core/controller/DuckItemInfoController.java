package cn.finduck.core.controller;

import cn.finduck.core.service.IDuckItemInfoService;
import cn.finduck.core.service.IDuckThemeService;
import cn.finduck.dto.DuckItemInfoDTO;
import cn.finduck.model.DuckItemInfoModel;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
@Api(tags = {"finduck-core"})
@RestController
@RequestMapping("/duckRssItem")


public class DuckItemInfoController {

    private final IDuckThemeService iDuckThemeService;
    private final IDuckItemInfoService iDuckItemInfoService;

    @ApiOperation("获取订阅信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "部门名称", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "parentId", value = "父级部门id", required = false, dataType = "Long", paramType = "query")
    })
    @RequestMapping(value = "/byConditions", method = RequestMethod.POST)
    public   SingleResponse<PageInfo<DuckItemInfoModel>> getDepartmentList(@RequestBody DuckItemInfoDTO dto) {
        return iDuckItemInfoService.selectDuckItemByCondition(dto);
    }


}
