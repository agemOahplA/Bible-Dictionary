package org.cruciata.dictserver.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DictRepository extends JpaRepository<Dict,String> {

}
