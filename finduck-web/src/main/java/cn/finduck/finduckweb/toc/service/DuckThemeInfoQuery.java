package cn.finduck.finduckweb.toc.service;

import cn.finduck.core.service.IDuckItemInfoService;
import cn.finduck.core.service.IDuckThemeService;
import cn.finduck.dto.DuckItemInfoDTO;
import cn.finduck.model.DuckItemInfoModel;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: UserViewPageQuery.java
 * @包 路 径： cn.finduck.finduckweb.toc.service
 * @版权所有：北京数字认证股份有限公司 (C) 2021
 * @类描述:
 * @版本: V1.0
 * @Author：SunQi
 * @创建时间：2022/4/25 10:58
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DuckThemeInfoQuery {
    private final IDuckItemInfoService iDuckItemInfoService;

    public SingleResponse<PageInfo<DuckItemInfoModel>> selectDuckItemByCondition(DuckItemInfoDTO dto){
        return iDuckItemInfoService.selectDuckItemByCondition(dto);
    }
}
