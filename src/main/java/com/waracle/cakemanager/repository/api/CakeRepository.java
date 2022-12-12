package com.waracle.cakemanager.repository.api;

import com.waracle.cakemanager.domain.CakeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeRepository extends CrudRepository<CakeEntity, Long> {
    boolean existsByTitle(String title);
}
