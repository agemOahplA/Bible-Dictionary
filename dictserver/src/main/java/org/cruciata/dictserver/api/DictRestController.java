package org.cruciata.dictserver.api;

import org.cruciata.dictserver.api.resource.DictWordVO;
import org.cruciata.dictserver.dataaccess.DictRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@Validated
public class DictRestController {

    private DictRepository dictRepository;

    DictRestController(DictRepository dictRepository){
        this.dictRepository = dictRepository;
    }

    @GetMapping
    public String users() {
        return "index";
    }

    @GetMapping("/dict")
    public Flux<List<DictWordVO>> getAllDict() {
        List<DictWordVO> all = dictRepository.findAllWork();
        return Flux.just(all);
    }

}
