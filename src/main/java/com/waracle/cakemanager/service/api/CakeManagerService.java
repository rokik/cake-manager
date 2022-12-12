package com.waracle.cakemanager.service.api;

import com.waracle.cakemanager.contract.Cake;

import java.util.List;

public interface CakeManagerService {
    List<Cake> findAllCakes();

    Cake addNewCake(Cake newCake);

    Cake updateExistingCake(Long cakeId, Cake cake);

    void deleteExistingCake(Long cakeId);

    Cake findByCakeId(Long cakeId);
}
