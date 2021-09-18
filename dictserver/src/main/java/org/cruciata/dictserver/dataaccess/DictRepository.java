package org.cruciata.dictserver.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictRepository extends JpaRepository<Dict, String> {

    List<Dict> findByWordLike(String key);

    List<Dict> findByExplanationLike(String key);
}
