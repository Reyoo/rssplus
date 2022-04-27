package cn.finduck.finduckweb.toc.controller;


import cn.finduck.dto.DuckItemInfoDTO;
import cn.finduck.finduckweb.infrastructure.facade.feign.DuckItemInfoFeign;
import cn.finduck.model.DuckItemInfoModel;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    private final DuckItemInfoFeign duckItemInfoFeign;

    @PostMapping(path = "search/rssName")
    public SingleResponse<PageInfo<DuckItemInfoModel>> getHomePageInfoWithRssName(@RequestBody DuckItemInfoDTO dto) {
//        SingleResponse<PageInfo<DuckItemInfoModel>> singleResponse = new SingleResponse<>();
        SingleResponse<PageInfo<DuckItemInfoModel>> singleResponse = duckItemInfoFeign.getRssByConditions(dto);
        if (singleResponse != null) {
            log.info("reponse:{}", JSONObject.toJSONString(singleResponse));
        }
        return singleResponse;

    }

}
