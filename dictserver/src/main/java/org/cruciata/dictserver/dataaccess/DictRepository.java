package org.cruciata.dictserver.dataaccess;

import org.cruciata.dictserver.api.resource.DictWordVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DictRepository extends JpaRepository<Dict,String> {

    @Query(value = "select word from dict",nativeQuery = true)
    List<DictWordVO> findAllWork();
}
