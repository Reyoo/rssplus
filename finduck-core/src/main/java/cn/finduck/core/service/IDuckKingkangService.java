package cn.finduck.core.service;

import cn.finduck.model.DuckKingkangModel;
import cn.finduck.model.DuckScheduleModel;
import cn.finduck.model.DuckThemeModel;
import cn.finduck.model.DuckThemeTypeModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

public interface IDuckKingkangService extends IService<DuckKingkangModel> {

    String uploadFile(MultipartFile file);

}
