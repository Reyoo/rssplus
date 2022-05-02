package cn.finduck.core.service.impl;

import cn.finduck.core.mapper.DuckKingkangModelMapper;
import cn.finduck.core.service.IDuckKingkangService;
import cn.finduck.model.DuckKingkangModel;
import cn.finduck.model.DuckScheduleModel;
import cn.finduck.model.DuckThemeModel;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: DuckThemeServiceImpl.java
 * @包 路 径： cn.finduck.core.service.impl
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述: 身份证号校验
 * @版本: V1.0 @创建人：SunQi
 * @创建时间：2022/4/24 11:13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class DuckKingkangServiceImpl extends ServiceImpl<DuckKingkangModelMapper, DuckKingkangModel> implements IDuckKingkangService {


    private final DuckKingkangModelMapper duckKingkangModelMapper;

    @Value("${finduck.uploadPathImg}")
    private String uploadPathImg;

    @Value("${finduck.virtualImgUrl}")
    private String virtualImgUrl;

    @Override
    public String uploadFile(MultipartFile file) {
        String fileName = "";
        try {
            if (file != null) {
                fileName = System.currentTimeMillis() + file.getOriginalFilename();
                String upload_file_dir = uploadPathImg;
                String destFileName = uploadPathImg + fileName;
                File upload_file_dir_file = new File(upload_file_dir);
                if (!upload_file_dir_file.exists()) {
                    upload_file_dir_file.mkdirs();
                }
                File targetFile = new File(upload_file_dir_file, fileName);
                file.transferTo(targetFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return virtualImgUrl + fileName;

    }
}
