package org.cruciata.dictserver;

import com.alibaba.fastjson.JSONArray;
import org.cruciata.dictserver.cache.CacheData;
import org.cruciata.dictserver.dataaccess.Dict;
import org.cruciata.dictserver.dataaccess.DictRepository;
import org.cruciata.dictserver.dataaccess.Everyday;
import org.cruciata.dictserver.dataaccess.EverydayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * 初始化数据
 */
@Component
public class DataInitializer2 implements CommandLineRunner {

    private EverydayRepository everydayRepository;

    @Autowired
    public DataInitializer2(EverydayRepository everydayRepository) {
        this.everydayRepository = everydayRepository;
    }

    private static String readJson() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL resource = classLoader.getResource("everyday.json");
        // 生成环境 resource 等于null 从docker volumes 读取BibleDictionary.json
        String filePath = resource==null?"/data/dictserver/everyday.json":resource.getFile();
        File file = new File(filePath);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = inputStreamReader.read()) != -1) {
                sb.append((char) ch);
            }
            inputStreamReader.close();
            return sb.toString().replaceAll("$oid", "");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String();
    }

    @Override
    public void run(String... args) {
        createEverydays();
    }

    private void createEverydays() {
        final Logger logger = LoggerFactory.getLogger(this.getClass());
        long count = everydayRepository.count();
        if (count == 0) {
            logger.info("初始化数据...");
            List<Everyday> everydayList = JSONArray.parseArray(readJson(), Everyday.class);
            everydayList.forEach(everyday -> everyday.setId(everyday.getId().substring(9, everyday.getId().length() - 2)));
            CacheData.RAW_Everyday.addAll(everydayList);
            everydayRepository.saveAll(everydayList);
        } else {
            logger.info("日课:{}", count);
        }
    }
}
