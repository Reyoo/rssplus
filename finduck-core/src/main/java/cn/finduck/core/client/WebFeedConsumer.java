package cn.finduck.core.client;


import cn.finduck.core.service.IDuckItemInfoService;
import cn.finduck.core.service.IDuckThemeService;
import cn.finduck.model.DuckItemInfoModel;
import cn.finduck.model.DuckThemeModel;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 封装请求类
 * @Author: QiSun
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebFeedConsumer {

    @Value("${finduck.url}")
    String ssrUrl;

    private final IDuckThemeService iDuckThemeService;

    private final IDuckItemInfoService iDuckItemInfoService;


    /**
     * 获取指定主题信息 如果为空则订阅所有
     *
     * @param themeDescs
     */
    public void saveThemeThenGetUrlPath(String... themeDescs) {
        HashMap<String, List<Map<String, String>>> themeNameAndDescUrl = new HashMap<>();

        QueryWrapper<DuckThemeModel> queryWrapper = new QueryWrapper<>();
        // 0禁用1启用
        queryWrapper.lambda().eq(DuckThemeModel::getThemeDeleted, 1);
        if (ArrayUtil.isNotEmpty(themeDescs)) {
            queryWrapper.lambda().in(DuckThemeModel::getThemeName, themeDescs);
        }
        queryWrapper.lambda().orderByDesc(DuckThemeModel::getThemeName);
        List<DuckThemeModel> list = iDuckThemeService.list(queryWrapper);
        Map<String, List<DuckThemeModel>> collect = list.stream().collect(Collectors.groupingBy(DuckThemeModel::getThemeName));
        collect.forEach((k, v) -> {
            saveWebInfoWithUrlPath(k, v);
        });

    }


    /**
     * @param themeName
     * @param duckThemeDTO
     * @return
     * @Description: 给定rss url 尾缀 获取rss 报文封装
     */
    public List<DuckItemInfoModel> saveWebInfoWithUrlPath(String themeName, List<DuckThemeModel> duckThemeDTO) {
        List<DuckItemInfoModel> duckItemInfoModels = new ArrayList<>();
        try {
            duckThemeDTO.stream().forEach(t -> {
                String url = ssrUrl.concat(t.getThemeUrlPrefix());
                try (XmlReader reader = new XmlReader(new URL(url))) {
                    SyndFeed feed = new SyndFeedInput().build(reader);
                    System.out.println(feed.getTitle());
                    System.out.println("***********************************");
                    for (SyndEntry entry : feed.getEntries()) {
                        DuckItemInfoModel duckItemInfoModel = DuckItemInfoModel.builder()
                                .banStatus(true)
                                .pubDate(LocalDateTime.now())
                                .msgThemeName(themeName)
                                .msgThemeId(t.getId())
                                .msgThemeDesc(t.getThemeDescription()).build();
                        if (StrUtil.isNotBlank(entry.getTitle())) {
                            duckItemInfoModel.setTitle(entry.getTitle());
                        }
                        if (StrUtil.isNotBlank(entry.getAuthor())) {
                            duckItemInfoModel.setAuthor(entry.getAuthor());
                        }
                        if (StrUtil.isNotBlank(entry.getLink())) {
                            duckItemInfoModel.setLink(entry.getLink());
                        }
                        if (StrUtil.isNotBlank(entry.getDescription().getValue())) {
                            duckItemInfoModel.setDescription(entry.getDescription().getValue());
                        }
                        duckItemInfoModels.add(duckItemInfoModel);
                    }
                    iDuckItemInfoService.saveBatch(duckItemInfoModels);
                } catch (MalformedURLException | FeedException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duckItemInfoModels;
    }

}
