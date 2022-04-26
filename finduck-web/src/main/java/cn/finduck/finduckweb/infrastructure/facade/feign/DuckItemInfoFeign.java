package cn.finduck.finduckweb.infrastructure.facade.feign;


import cn.finduck.dto.DuckItemInfoDTO;
import cn.finduck.model.DuckItemInfoModel;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 尘锋科技
 *
 * @author chenzw
 * @version 1.0
 * @description: 调用企业微信接口同步数据
 * @date 2021/4/7
 */
@FeignClient(value = "finduckCore")
@RequestMapping("/duckRssItem")
public interface DuckItemInfoFeign {


    /**
     * 根据条件获取订阅信息
     * @param dto
     * @return
     */
    @PostMapping("/byConditions")
    @ApiOperation(value = "根据条件获取订阅信息")
    SingleResponse<PageInfo<DuckItemInfoModel>> getRssByConditions(@RequestBody DuckItemInfoDTO dto);

}


