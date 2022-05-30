package cn.finduck.finduckweb.toc.controller;


import cn.finduck.dto.DuckItemInfoDTO;
import cn.finduck.dto.DuckNavigationDTO;
import cn.finduck.dto.DuckWebCellDTO;
import cn.finduck.finduckweb.infrastructure.facade.feign.DuckCoreFegin;
import cn.finduck.model.DuckItemInfoModel;
import cn.finduck.model.DuckWebCellModel;
import cn.finduck.vo.DuckKingkangVO;
import cn.finduck.vo.DuckNavigationVO;
import cn.finduck.vo.DuckWebCellVO;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: UserViewPageController.java
 * @包 路 径： cn.finduck.finduckweb.toc.controller
 * @类描述: 用户首页
 * @版本: V1.0
 * @Author: SunQi
 * @创建时间：2022/4/25 10:02
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/rss/home")
@Slf4j
public class UserViewPageController {

    private final DuckCoreFegin duckCoreFegin;

    @PostMapping(path = "search/rssName")
    public SingleResponse<PageInfo<DuckItemInfoModel>> getHomePageInfoWithRssName(@RequestBody DuckItemInfoDTO dto) {
//        SingleResponse<PageInfo<DuckItemInfoModel>> singleResponse = new SingleResponse<>();
        SingleResponse<PageInfo<DuckItemInfoModel>> singleResponse = duckCoreFegin.getRssByConditions(dto);
        if (singleResponse != null) {
            log.info("reponse:{}", JSONObject.toJSONString(singleResponse));
        }
        return singleResponse;

    }

    /**
     * 金刚位显示 固定15个
     *
     * @return
     */
    @GetMapping(path = "kk/list")
    public SingleResponse<List<DuckKingkangVO>> list() {
        SingleResponse<List<DuckKingkangVO>> singleResponse = duckCoreFegin.kingKanglist();
        if (singleResponse != null) {
            log.info("reponse:{}", JSONObject.toJSONString(singleResponse));
        }
        return singleResponse;
    }


    /**
     * 内容栏目分页
     *
     * @param dto
     * @return
     */
    @PostMapping("cell/page")
    @ApiOperation(value = "根据条件获取订阅信息")
    SingleResponse<Page<DuckWebCellModel>> byConditions(@RequestBody DuckWebCellDTO dto) {
        SingleResponse<Page<DuckWebCellModel>> pageSingleResponse = duckCoreFegin.byConditions(dto);
        if (pageSingleResponse != null) {
            log.info("reponse:{}", JSONObject.toJSONString(pageSingleResponse));
        }
        return pageSingleResponse;
    }

    /**
     * 内容栏目
     *
     * @param pageInfo
     * @param pageSize
     * @return
     */
    @PostMapping("cell/typePage")
    @ApiOperation(value = "根据类型查询主题")
    SingleResponse<List<DuckWebCellVO>> pageWebCell(Integer pageInfo, Integer pageSize) {
        SingleResponse<List<DuckWebCellVO>> listSingleResponse = duckCoreFegin.pageWebCell(pageInfo, pageSize);
        if (listSingleResponse != null) {
            log.info("reponse:{}", JSONObject.toJSONString(listSingleResponse));
        }
        return listSingleResponse;
    }

    @ApiOperation("查询主题")
    @RequestMapping(value = "nav/get", method = RequestMethod.GET)
    public SingleResponse<List<DuckNavigationVO>> byConditions() {
        SingleResponse<List<DuckNavigationVO>> listSingleResponse = duckCoreFegin.navByConditions();
        if (listSingleResponse != null) {
            log.info("reponse:{}", JSONObject.toJSONString(listSingleResponse));
        }
        return listSingleResponse;
    }

    @ApiOperation("查询主题")
    @RequestMapping(value = "nav/page", method = RequestMethod.POST)
    public SingleResponse<DuckWebCellVO> pageByItemId(@RequestBody DuckNavigationDTO dto) {
        SingleResponse<DuckWebCellVO> duckWebCellVOSingleResponse = duckCoreFegin.navPageByItemId(dto);
        if (duckWebCellVOSingleResponse != null) {
            log.info("reponse:{}", JSONObject.toJSONString(duckWebCellVOSingleResponse));
        }
        return duckWebCellVOSingleResponse;
    }

}
