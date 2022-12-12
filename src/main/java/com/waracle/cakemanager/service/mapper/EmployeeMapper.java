package com.waracle.cakemanager.service.mapper;

import com.waracle.cakemanager.contract.Employee;
import com.waracle.cakemanager.domain.EmployeeEntity;

public class EmployeeMapper {
    public static Employee mapToRecord(EmployeeEntity employee) {
        if (employee == null) {
            return null;
        }

        return new Employee(
                employee.getEmployeeId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail());
    }

    public static EmployeeEntity mapToEntity(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeEntity entity = new EmployeeEntity();
        entity.setEmployeeId(employee.employeeId());
        entity.setFirstName(employee.firstName());
        entity.setLastName(employee.lastName());
        entity.setEmail(employee.email());
        return entity;
    }
}
