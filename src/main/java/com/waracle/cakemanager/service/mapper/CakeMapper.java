package com.waracle.cakemanager.service.mapper;

import com.waracle.cakemanager.contract.Cake;
import com.waracle.cakemanager.domain.CakeEntity;

public class CakeMapper {
    public static Cake mapToRecord(CakeEntity entity) {
        return new Cake(
                entity.getCakeId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getImageUrl(),
                EmployeeMapper.mapToRecord(entity.getEmployee()));
    }

    public static CakeEntity mapToCakeEntity(Cake cake) {
        CakeEntity entity = new CakeEntity();
        entity.setCakeId(cake.cakeId());
        entity.setTitle(cake.title());
        entity.setDescription(cake.desc());
        entity.setImageUrl(cake.image());
        entity.setEmployee(EmployeeMapper.mapToEntity(cake.employee()));
        return entity;
    }
}
