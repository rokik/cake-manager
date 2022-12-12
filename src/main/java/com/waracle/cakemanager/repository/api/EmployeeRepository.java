package com.waracle.cakemanager.repository.api;

import com.waracle.cakemanager.domain.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
}
