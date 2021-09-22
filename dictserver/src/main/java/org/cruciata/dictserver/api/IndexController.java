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


    @GetMapping
    public String index(String key, Model model) {

        if (!StringUtils.isEmpty(key)) {
            key = key.trim();
            model.addAttribute("key", key);
            List<Dict> wordLike = dictRepository.findByWordLike("%" + key + "%");
            model.addAttribute("wordLike", wordLike);
            List<Dict> explanationLike = dictRepository.findByExplanationLike("%" + key + "%");
            String finalKey = key;
            String mark = "<mark>"+finalKey+"</mark>";
            explanationLike.forEach(dict -> {
                int length = dict.getExplanation().length();
                int index = dict.getExplanation().indexOf(finalKey);
                int min = Math.min(length, index + 20);
                if (min == length) {
                    int max = Math.max(0, index - 20);
                    dict.setExplanation(dict.getExplanation().substring(max, index).replace(finalKey,mark));
                } else {
                    dict.setExplanation(dict.getExplanation().substring(index, min).replace(finalKey,mark));
                }
            });

            model.addAttribute("explanationLike", explanationLike);
        }

        return "index";
    }

    @ModelAttribute("rowColData")
    List<List<Dict>> allDictWord() {
        List<Dict> dictList = dictRepository.findAll();

        int col = 6;
        List<List<Dict>> rowColData = new ArrayList<>();
        int fromIndex = 0;
        int toIndex = col;

        while (toIndex != dictList.size()) {
            rowColData.add(dictList.subList(fromIndex, toIndex));
            fromIndex = toIndex;
            toIndex = toIndex + col;
            if (toIndex > dictList.size()) {
                toIndex = dictList.size();
                rowColData.add(dictList.subList(fromIndex, toIndex));
            }
        }


        return rowColData;
    }

}
