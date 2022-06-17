package cn.finduck.core.controller;

import cn.finduck.core.service.IDuckItemInfoService;
import cn.finduck.core.service.IDuckThemeService;
import cn.finduck.dto.DuckItemInfoDTO;
import cn.finduck.model.DuckItemInfoModel;
import com.alibaba.cola.dto.SingleResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
@Api(tags = {"首页接口"})
@RestController
@RequestMapping("/duckRssItem")
public class DuckItemInfoController {

    private final IDuckThemeService iDuckThemeService;
    private final IDuckItemInfoService iDuckItemInfoService;

    @ApiOperation("获取订阅信息")
    @RequestMapping(value = "/byConditions", method = RequestMethod.POST)
    public Page<DuckItemInfoModel> getDepartmentList(@RequestBody DuckItemInfoDTO dto) {
        return iDuckItemInfoService.selectDuckItemByCondition(dto,dto.getPage() == null ? 0 : dto.getPage(), dto.getPageSize() == null ? 10 : dto.getPageSize());
    }


}
