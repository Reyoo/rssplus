package cn.finduck.core.controller;

import cn.finduck.core.service.IDuckTypeService;
import cn.finduck.core.service.IDuckWebCellService;
import cn.finduck.dto.DuckItemInfoDTO;
import cn.finduck.dto.DuckNavigationDTO;
import cn.finduck.model.DuckThemeModel;
import cn.finduck.model.DuckTypeModel;
import cn.finduck.model.DuckWebCellModel;
import cn.finduck.vo.DuckNavigationVO;
import cn.finduck.vo.DuckWebCellVO;
import com.alibaba.cola.dto.SingleResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckNavigationController.java
 * @包 路 径： cn.finduck.core.controller
 * @类描述:
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/5/2 16:24
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Api(tags = "导航")
@RestController
@RequestMapping("/nav")
public class DuckNavigationController {


    private final IDuckTypeService duckThemeModel;

    private final IDuckWebCellService iDuckWebCellService;


    @ApiOperation("查询主题")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public SingleResponse<List<DuckNavigationVO>> byConditions() {
        Page<DuckTypeModel> page = duckThemeModel.pageByCondition(null, 1, 8);
        List<DuckNavigationVO> duckNavigationVOS = new ArrayList<>();
        List<DuckTypeModel> records = page.getRecords();
        records.stream().forEach(t -> {
            DuckNavigationVO duckNavigationVO = new DuckNavigationVO();
            BeanUtils.copyProperties(t, duckNavigationVO);
            duckNavigationVOS.add(duckNavigationVO);

        });
        return SingleResponse.of(duckNavigationVOS);
    }

    @ApiOperation("查询主题")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public SingleResponse<DuckWebCellVO> pageByItemId(@RequestBody DuckNavigationDTO dto) {
        DuckWebCellVO duckWebCellVO = iDuckWebCellService.pageByItemId(dto);
        return SingleResponse.of(duckWebCellVO);
    }
}
