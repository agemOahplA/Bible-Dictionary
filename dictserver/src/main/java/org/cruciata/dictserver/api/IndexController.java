package org.cruciata.dictserver.api;

import org.cruciata.dictserver.api.resource.DictWordVO;
import org.cruciata.dictserver.dataaccess.Dict;
import org.cruciata.dictserver.dataaccess.DictRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    private DictRepository dictRepository;

    IndexController(DictRepository dictRepository){
        this.dictRepository = dictRepository;
    }


    @GetMapping
    public String index() {
        return "index";
    }

    @ModelAttribute("allDict")
    List<Dict> allDictWord() {
        List<Dict> allDict = dictRepository.findAll();
        return allDict;
    }

}
