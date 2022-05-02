package cn.finduck.core.controller;

import cn.finduck.constant.FinduckConstants;
import cn.finduck.core.service.IDuckKingkangService;
import cn.finduck.core.service.IDuckWebCellService;
import cn.finduck.dto.DuckKingkangDTO;
import cn.finduck.dto.DuckTypeDTO;
import cn.finduck.dto.DuckWebCellDTO;
import cn.finduck.model.DuckKingkangModel;
import cn.finduck.model.DuckTypeModel;
import cn.finduck.model.DuckWebCellModel;
import cn.finduck.vo.DuckWebCellVO;
import com.alibaba.cola.dto.SingleResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckWebCellController.java
 * @包 路 径： cn.finduck.core.controller
 * @类描述: 首页单元格显示
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/5/1 16:29
 */

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Api(tags = {"web页展示"})
@RestController
@RequestMapping("/cell")
public class DuckWebCellController {
    private final IDuckWebCellService iDuckWebCellService;

    @ApiOperation("瀑布分页显示内容-根据主题ID 确认应该")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public SingleResponse<Page<DuckWebCellModel>> byConditions(@RequestBody DuckWebCellDTO dto) {
        DuckWebCellModel duckKingkangModel = new DuckWebCellModel();
        BeanUtils.copyProperties(dto, duckKingkangModel);
        Page<DuckWebCellModel> duckTypeModelPage = iDuckWebCellService.getPageWithTypeByCondition(duckKingkangModel, dto.getPage() == null ? 0 : dto.getPage(), dto.getPageSize() == null ? 10 : dto.getPageSize());
        return SingleResponse.of(duckTypeModelPage);
    }


    @ApiOperation("瀑布分页显示内容-根据主题ID 确认应该")
    @RequestMapping(value = "/typePage", method = RequestMethod.POST)
    public SingleResponse<List<DuckWebCellVO>> pageWebCell(Integer pageInfo, Integer pageSize) {
        if (pageInfo == null) pageInfo = 0;
        if (pageSize == null) pageSize = 10;
        List<DuckWebCellVO> listingPage = iDuckWebCellService.pageWebCell(pageInfo, pageSize);
        return SingleResponse.of(listingPage);
    }







}
