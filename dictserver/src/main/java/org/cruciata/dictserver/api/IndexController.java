package org.cruciata.dictserver.api;

import org.cruciata.dictserver.dataaccess.Dict;
import org.cruciata.dictserver.dataaccess.DictRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    private DictRepository dictRepository;

    IndexController(DictRepository dictRepository) {
        this.dictRepository = dictRepository;
    }

    /**
     * 首页
     * @param key
     * @param model
     * @return
     */
    @GetMapping
    public String index(String key, Model model) {

        if (!StringUtils.isEmpty(key)) {
            key = key.trim();
            model.addAttribute("key", key);
            // 查找符合关键字的词
            List<Dict> wordLike = dictRepository.findByWordLike("%" + key + "%");
            model.addAttribute("wordLike", wordLike);
            // 查找符合关键字的内容数据
            List<Dict> explanationLike = dictRepository.findByExplanationLike("%" + key + "%");
            String finalKey = key;
            // 前端对关键字进行标记
            String mark = "<mark>"+finalKey+"</mark>";
            explanationLike.forEach(dict -> {
                // 内容长度
                int length = dict.getExplanation().length();
                // 关键字第一次出现的位置
                int index = dict.getExplanation().indexOf(finalKey);
                // 取最小对
                int min = Math.min(length, index + 20);
                if (min == length) {
                    // 取最大对
                    int max = Math.max(0, index - 20);
                    // 关键字增加标记
                    dict.setExplanation(dict.getExplanation().substring(max, index).replace(finalKey,mark));
                } else {
                    // 关键字增加标记
                    dict.setExplanation(dict.getExplanation().substring(index, min).replace(finalKey,mark));
                }
            });

            model.addAttribute("explanationLike", explanationLike);
        }

        return "index";
    }

    /**
     * 访问首页的时候触发此方法 作为addAttribute返回数据
     * @return
     */
    @ModelAttribute("rowColData")
    List<List<Dict>> allDictWord() {
        // 查找所有
        List<Dict> dictList = dictRepository.findAll();
        // 每行显示的列数
        int col = 6;
        List<List<Dict>> rowColData = new ArrayList<>();
        // 开始
        int fromIndex = 0;
        // 结束
        int toIndex = col;

        while (toIndex != dictList.size()) {
            // 读取fromIndex到toIndex的数据
            rowColData.add(dictList.subList(fromIndex, toIndex));
            // 重新计算位置
            fromIndex = toIndex;
            // 重新计算位置
            toIndex = toIndex + col;
            if (toIndex > dictList.size()) {
                // 下标越界 重置
                toIndex = dictList.size();
                // 读取最后到数据
                rowColData.add(dictList.subList(fromIndex, toIndex));
            }
        }

        return rowColData;
    }

}
