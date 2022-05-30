package cn.finduck.finduckweb.infrastructure.facade.feign;

import cn.finduck.dto.*;
import cn.finduck.finduckweb.infrastructure.config.MultipartSupportConfig;
import cn.finduck.model.DuckItemInfoModel;
import cn.finduck.model.DuckThemeModel;
import cn.finduck.model.DuckTypeModel;
import cn.finduck.model.DuckWebCellModel;
import cn.finduck.vo.DuckKingkangVO;
import cn.finduck.vo.DuckNavigationVO;
import cn.finduck.vo.DuckWebCellVO;
import com.alibaba.cola.dto.SingleResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckCoreFegin.java
 * @包 路 径： cn.finduck.finduckweb.infrastructure.facade.feign
 * @版权所有：北京数字认证股份有限公司 (C) 2021
 * @类描述:
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/5/2 18:19
 */
@FeignClient(name = "finduckCore",configuration = MultipartSupportConfig.class)
public interface DuckCoreFegin {

    @PostMapping("/duckRssItem/byConditions")
    @ApiOperation(value = "根据条件获取订阅信息")
    SingleResponse<PageInfo<DuckItemInfoModel>> getRssByConditions(@RequestBody DuckItemInfoDTO dto);


    @GetMapping("/kk/list")
    @ApiOperation(value = "根据条件获取订阅信息")
    SingleResponse<List<DuckKingkangVO>> kingKanglist();

    @ApiOperation(value = "新增或者更新")
    @PostMapping(value= "/kk/saveUpOrUpUp")
//    SingleResponse<Boolean> kingKangSaveOrUpdate(@RequestPart(name = "file") MultipartFile file);
    SingleResponse<Boolean> kingKangSaveOrUpdate(@RequestBody  DuckKingkangUpLoadDTO dto);


    @ApiOperation("查询主题")
    @RequestMapping(value = "nav/get", method = RequestMethod.GET)
    SingleResponse<List<DuckNavigationVO>> navByConditions();

    @ApiOperation("分页")
    @RequestMapping(value = "nav/page", method = RequestMethod.POST)
    SingleResponse<DuckWebCellVO> navPageByItemId(@RequestBody DuckNavigationDTO dto);



    @ApiOperation("新增主题-关联类型")
    @RequestMapping(value = "theme/addOrUpdate", method = RequestMethod.POST)
    SingleResponse<Boolean> themeAddOrUpdate(@RequestBody DuckItemInfoDTO dto);


    @ApiOperation("查询主题")
    @RequestMapping(value = "theme/byConditions", method = RequestMethod.POST)
    SingleResponse<Page<DuckThemeModel>> themeByConditions(@RequestBody DuckItemInfoDTO dto);

    @ApiOperation("根据类型查询主题")
    @RequestMapping(value = "theme/getThemeByType", method = RequestMethod.POST)
    SingleResponse<Page<DuckThemeModel>> getThemeByType(@RequestBody DuckThemeTypeDTO dto);


    @ApiOperation("删除主题")
    @RequestMapping(value = "theme/delTheme", method = RequestMethod.DELETE)
    SingleResponse<Boolean> delTheme(@RequestBody DuckThemeDTO dto);

    @ApiOperation("新增或修改消息类型")
    @RequestMapping(value = "type/saveOrUpdate", method = RequestMethod.POST)
    SingleResponse<Boolean> saveOrUpdateDuckType(@RequestBody DuckTypeModel dto);

    @ApiOperation("查询类型")
    @RequestMapping(value = "type/page", method = RequestMethod.POST)
    SingleResponse<Page<DuckTypeModel>> getPageByConditions(@RequestBody DuckTypeDTO dto);

    @ApiOperation("删除类型")
    @RequestMapping(value = "type/delType", method = RequestMethod.DELETE)
    SingleResponse<Boolean> delTheme(@RequestBody DuckTypeDTO dto);


    @GetMapping("/cell/page")
    @ApiOperation(value = "根据条件获取订阅信息")
    SingleResponse<Page<DuckWebCellModel>> byConditions(@RequestBody DuckWebCellDTO dto);

    @PostMapping("/cell/typePage")
    @ApiOperation(value = "新增或者更新")
    SingleResponse<List<DuckWebCellVO>> pageWebCell(@RequestParam("pageInfo") Integer pageInfo, @RequestParam("pageSize") Integer pageSize);


}
