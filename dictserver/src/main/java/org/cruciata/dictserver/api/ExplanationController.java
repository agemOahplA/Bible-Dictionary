package org.cruciata.dictserver.api;

import org.cruciata.dictserver.dataaccess.Dict;
import org.cruciata.dictserver.dataaccess.DictRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/explanation")
public class ExplanationController {


    private static final String PATH_VARIABLE_DICT_ID = "dictId";

    private static final String PATH_DICT_ID = "{" + PATH_VARIABLE_DICT_ID + "}";

    private DictRepository dictRepository;

    ExplanationController(DictRepository dictRepository) {
        this.dictRepository = dictRepository;
    }


    @GetMapping("/" + PATH_DICT_ID)
    public String explanation(@PathVariable(PATH_VARIABLE_DICT_ID) String dictId,String key, Model model) {
        System.out.println(key);
        Optional<Dict> optionalDict = dictRepository.findById(dictId);

        //循环修改所有key
        String mark = "<mark>"+key+"</mark>";
        optionalDict.ifPresent(dict -> {
            if(!StringUtils.isEmpty(key)){
                //替换key
                dict.setExplanation(dict.getExplanation().replaceAll(key,mark));
            }
        });
        //添加返回model
        model.addAttribute("dict",optionalDict.get());
        //返回页面
        return "explanation";
    }
}
