package cn.finduck.core;

import cn.finduck.core.service.IDuckItemInfoService;
import cn.finduck.core.service.IDuckThemeService;
import cn.finduck.model.DuckItemInfoModel;
import cn.finduck.model.DuckThemeModel;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class CoreApplicationTests {

    @Autowired
    IDuckThemeService iDuckThemeService;

    @Value("${finduck.url}")
    String ssrUrl;

    @Autowired
    IDuckItemInfoService iDuckItemInfoService;

    @Test
    void contextLoads() throws IOException {

        Document document = Jsoup.connect("https://tophub.today/")
                .timeout(20000)
                .header("Accept", "application/json, text/javascript, */*; q=0.01")
//                        .header("Content-Type", "application/json; charset=gb2312")
                .header("Content-Type", "application/json; charset=UTF-8")
//                .header("User-Agent", FindFishUserAgentUtil.randomUserAgent())
                .header("User-Agent", "com.apple.WebKit.Networking/8610.3.7.0.3 CFNetwork/1209 Darwin/20.2.0")
                .header("X-Requested-With", "XMLHttpRequest")
                .method(Connection.Method.GET)
                .ignoreContentType(true).get();
        System.out.println(document.toString());

    }

    @Test
    void getWebInfoWithUrlPath() throws IOException {

//        Document document = Jsoup.connect("https://rss-2ikz.vercel.app/daxiaamu/home")
//                .timeout(20000)
//                .header("Accept", "application/json, text/javascript, */*; q=0.01")
////                        .header("Content-Type", "application/json; charset=gb2312")
//                .header("Content-Type", "application/json; charset=UTF-8")
////                .header("User-Agent", FindFishUserAgentUtil.randomUserAgent())
//                .header("User-Agent", "com.apple.WebKit.Networking/8610.3.7.0.3 CFNetwork/1209 Darwin/20.2.0")
//                .header("X-Requested-With", "XMLHttpRequest")
//                .method(Connection.Method.GET)
//                .ignoreContentType(true).get();
//        System.out.println(document.toString());


        try {
            String url = "https://rss-2ikz.vercel.app/zhihu/pin/daily";

            try (XmlReader reader = new XmlReader(new URL(url))) {
                SyndFeed feed = new SyndFeedInput().build(reader);
                System.out.println(feed.getTitle());
                System.out.println("***********************************");

                for (SyndEntry entry : feed.getEntries()) {
                    System.out.println(entry);
                    System.out.println("***********************************");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testlambda() {
        String[] themeDescs = new String[]{};
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

                        System.out.println(entry.toString());

//                        DuckItemInfoModel duckItemInfoModel = DuckItemInfoModel.builder()
//                                .banStatus(true)
//                                .pubDate(LocalDateTime.now())
//                                .msgThemeName(themeName)
//                                .msgThemeId(t.getId())
//                                .msgThemeDesc(t.getThemeDescription()).build();
//                        if (StrUtil.isNotBlank(entry.getTitle())) {
//                            duckItemInfoModel.setTitle(entry.getTitle());
//                        }
//                        if (StrUtil.isNotBlank(entry.getAuthor())) {
//                            duckItemInfoModel.setAuthor(entry.getAuthor());
//                        }
//                        if (StrUtil.isNotBlank(entry.getLink())) {
//                            duckItemInfoModel.setLink(entry.getLink());
//                        }
//                        if (StrUtil.isNotBlank(entry.getDescription().getValue())) {
//                            duckItemInfoModel.setDescription(entry.getDescription().getValue());
//                        }
//                        iDuckItemInfoService.save(duckItemInfoModel);
//                        duckItemInfoModels.add(duckItemInfoModel);
                    }
//                    iDuckItemInfoService.saveBatch(duckItemInfoModels);
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
