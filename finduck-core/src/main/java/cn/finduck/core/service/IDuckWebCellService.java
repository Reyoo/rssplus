package cn.finduck.core.service;

import cn.finduck.dto.DuckNavigationDTO;
import cn.finduck.model.DuckKingkangModel;
import cn.finduck.model.DuckThemeTypeModel;
import cn.finduck.model.DuckWebCellModel;
import cn.finduck.vo.DuckWebCellVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IDuckWebCellService extends IService<DuckWebCellModel> {

    Page<DuckWebCellModel> getPageWithTypeByCondition(DuckWebCellModel dto, int page, int pageSize);

    List<DuckWebCellVO> pageWebCell(int pageInfo, int pageSize);


    DuckWebCellVO pageByItemId( DuckNavigationDTO dto);
}
