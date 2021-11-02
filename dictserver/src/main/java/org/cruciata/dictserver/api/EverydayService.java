package org.cruciata.dictserver.api;

import org.cruciata.dictserver.dataaccess.Everyday;
import org.cruciata.dictserver.dataaccess.EverydayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/everyday")
@RestController
public class EverydayService {

    private EverydayRepository everydayRepository;

    EverydayService(EverydayRepository everydayRepository){
        this.everydayRepository = everydayRepository;
    }


    @GetMapping
    public List<Everyday> get(){
        Pageable pageable = PageRequest.of(0,10);
        Page<Everyday> all = everydayRepository.findAll(pageable);
        return all.toList();
    }
}
