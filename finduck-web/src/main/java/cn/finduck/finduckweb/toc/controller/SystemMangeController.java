package cn.finduck.finduckweb.toc.controller;


import cn.finduck.dto.*;
import cn.finduck.finduckweb.infrastructure.facade.feign.DuckCoreFegin;
import cn.finduck.model.DuckKingkangModel;
import cn.finduck.model.DuckThemeModel;
import cn.finduck.model.DuckTypeModel;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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
@RequestMapping("/rss/admin")
@Slf4j
public class SystemMangeController {

    private final DuckCoreFegin duckCoreFegin;


    /**
     * 金刚位置新增
     * @return
     */
    @PostMapping("kk/saveUpOrUpUp")
    @ApiOperation(value = "新增或者更新")
    public SingleResponse<Boolean> kingKangSaveOrUpdate(@RequestPart(name = "file",required = false) MultipartFile file,@RequestPart(name = "model") DuckKingkangModel model) {
//    public SingleResponse<Boolean> kingKangSaveOrUpdate(@RequestBody  DuckKingkangUpLoadDTO dto) {
        DuckKingkangUpLoadDTO duckKingkangUpLoadDTO = new DuckKingkangUpLoadDTO();
        BeanUtils.copyProperties(model,duckKingkangUpLoadDTO);
        duckKingkangUpLoadDTO.setFile(file);
        SingleResponse<Boolean> singleResponse = duckCoreFegin.kingKangSaveOrUpdate(duckKingkangUpLoadDTO);
        if (singleResponse != null) {
            log.info("reponse:{}", JSONObject.toJSONString(singleResponse));
        }
        return singleResponse;
    }

    @ApiOperation("新增主题-关联类型")
    @RequestMapping(value = "theme/addOrUpdate", method = RequestMethod.POST)
    public SingleResponse<Boolean> themeAddOrUpdate(@RequestBody DuckItemInfoDTO dto) {
        Assert.notNull(dto.getTitle(), "标题不能为空");
        Assert.notNull(dto.getLink(), "链接不能为空");
        SingleResponse<Boolean> booleanSingleResponse = duckCoreFegin.themeAddOrUpdate(dto);
        if (booleanSingleResponse != null) {
            log.info("reponse:{}", JSONObject.toJSONString(booleanSingleResponse));
        }
        return booleanSingleResponse;
    }

    @ApiOperation("查询主题")
    @RequestMapping(value = "theme/byConditions", method = RequestMethod.POST)
    public SingleResponse<Page<DuckThemeModel>> getThemeByConditions(@RequestBody DuckItemInfoDTO dto) {
        SingleResponse<Page<DuckThemeModel>> pageSingleResponse = duckCoreFegin.themeByConditions(dto);
        return pageSingleResponse;
    }

    @ApiOperation("根据类型查询主题")
    @RequestMapping(value = "theme/getByType", method = RequestMethod.POST)
    public SingleResponse<Page<DuckThemeModel>> getThemeByType(@RequestBody DuckThemeTypeDTO dto) {
        SingleResponse<Page<DuckThemeModel>> pageSingleResponse = duckCoreFegin.getThemeByType(dto);
        return pageSingleResponse;
    }

    @ApiOperation("删除主题")
    @RequestMapping(value = "/delTheme", method = RequestMethod.DELETE)
    public SingleResponse<Boolean> delTheme(@RequestBody DuckThemeDTO dto) {
        Assert.notNull(dto.getId(), "主键不能为空");
        SingleResponse<Boolean> booleanSingleResponse = duckCoreFegin.delTheme(dto);
        return booleanSingleResponse;
    }

    /**
     * 类型
     */

    @ApiOperation("新增或修改消息类型")
    @RequestMapping(value = "/typeSaveOrUpdate", method = RequestMethod.POST)
    public SingleResponse<Boolean> saveOrUpdateDuckType(@RequestBody DuckTypeModel dto) {
        SingleResponse<Boolean> booleanSingleResponse = duckCoreFegin.saveOrUpdateDuckType(dto);
        return booleanSingleResponse;
    }


    @ApiOperation("查询类型")
    @RequestMapping(value = "/pageType", method = RequestMethod.POST)
    public SingleResponse<Page<DuckTypeModel>> getPageType(@RequestBody DuckTypeDTO dto) {
        SingleResponse<Page<DuckTypeModel>> pageByConditions = duckCoreFegin.getPageByConditions(dto);
        return pageByConditions;
    }

    @ApiOperation("删除类型")
    @RequestMapping(value = "/delType", method = RequestMethod.DELETE)
    public SingleResponse<Boolean> delTypeByConditions(@RequestBody DuckTypeDTO dto) {
        SingleResponse<Boolean> booleanSingleResponse = duckCoreFegin.delTheme(dto);
        return booleanSingleResponse;
    }
}
