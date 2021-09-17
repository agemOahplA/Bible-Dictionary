package org.cruciata.dictserver;

import org.cruciata.dictserver.dataaccess.Dict;
import org.cruciata.dictserver.dataaccess.DictRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DictserverApplicationTests {

    @Autowired
    DictRepository dictRepository;

    @Test
    void contextLoads() {

        List<Dict> all = dictRepository.findAll();
        System.out.println(all);

    }

}
