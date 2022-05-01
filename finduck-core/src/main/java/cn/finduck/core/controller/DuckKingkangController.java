package cn.finduck.core.controller;

import cn.finduck.constant.FinduckConstants;
import cn.finduck.core.service.IDuckKingkangService;
import cn.finduck.model.DuckKingkangModel;
import cn.finduck.model.DuckScheduleModel;
import com.alibaba.cola.dto.SingleResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckKingkangController.java
 * @包 路 径： cn.finduck.core.controller
 * @类描述: 金刚位置显示
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/5/1 16:29
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Api(tags = {"金刚位置"})
@RestController
@RequestMapping("/kk")
public class DuckKingkangController {

    private final IDuckKingkangService iDuckKingkangService;

    @ApiOperation("获取金刚位置-固定显示15条记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public SingleResponse<List<DuckKingkangModel>> byConditions() {
        QueryWrapper<DuckKingkangModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DuckKingkangModel::getKingkangStatus, FinduckConstants.ONUSE);
        queryWrapper.lambda().orderByDesc(DuckKingkangModel::getKingkangOrder);
        List<DuckKingkangModel> list = iDuckKingkangService.list(queryWrapper);
        return SingleResponse.of(list);
    }

}
