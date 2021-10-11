package org.cruciata.dictserver;

import com.alibaba.fastjson.JSONArray;
import org.cruciata.dictserver.cache.CacheData;
import org.cruciata.dictserver.dataaccess.Dict;
import org.cruciata.dictserver.dataaccess.DictRepository;
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
public class DataInitializer implements CommandLineRunner {

    private DictRepository dictRepository;

    @Autowired
    public DataInitializer(DictRepository dictRepository) {
        this.dictRepository = dictRepository;
    }

    private static String readJson() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL resource = classLoader.getResource("BibleDictionary.json");
        // 生成环境 resource 等于null 从docker volumes 读取BibleDictionary.json
        String filePath = resource==null?"/data/dictserver/BibleDictionary.json":resource.getFile();
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
        return "";
    }

    @Override
    public void run(String... args) {
        createDicts();
    }

    private void createDicts() {
        final Logger logger = LoggerFactory.getLogger(this.getClass());
        long count = dictRepository.count();
        if (count == 0) {
            logger.info("初始化数据...");
            List<Dict> dictList = JSONArray.parseArray(readJson(), Dict.class);
            dictList.forEach(dict -> dict.setId(dict.getId().substring(9, dict.getId().length() - 2)));
            CacheData.RAW_DICT.addAll(dictList);
            dictRepository.saveAll(dictList);
        } else {
            logger.info("词数量:{}", count);
        }
    }
}
