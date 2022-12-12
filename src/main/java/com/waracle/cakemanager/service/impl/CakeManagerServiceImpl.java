package com.waracle.cakemanager.service.impl;

import com.waracle.cakemanager.contract.Cake;
import com.waracle.cakemanager.domain.CakeEntity;
import com.waracle.cakemanager.exception.CakeAlreadyExistsException;
import com.waracle.cakemanager.exception.CakeNotFoundException;
import com.waracle.cakemanager.repository.api.CakeRepository;
import com.waracle.cakemanager.service.api.CakeManagerService;
import com.waracle.cakemanager.service.mapper.CakeMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class CakeManagerServiceImpl implements CakeManagerService {
    private final CakeRepository cakeRepository;

    protected CakeManagerServiceImpl(final CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    @Override
    public List<Cake> findAllCakes() {
        List<Cake> cakeList = new LinkedList<>();
        cakeRepository.findAll().forEach(entity -> {
            cakeList.add(CakeMapper.mapToRecord(entity));
        });
        return cakeList;
    }

    @Override
    @Transactional
    public Cake addNewCake(Cake newCake) {
        if (cakeRepository.existsByTitle(newCake.title())) {
            throw new CakeAlreadyExistsException();
        }
        CakeEntity entity = CakeMapper.mapToCakeEntity(newCake);
        entity = cakeRepository.save(entity);
        return CakeMapper.mapToRecord(entity);
    }

    @Override
    @Transactional
    public Cake updateExistingCake(Long cakeId, Cake cake) {
        if (cakeRepository.existsById(cakeId)) {
            CakeEntity entity = CakeMapper.mapToCakeEntity(cake);
            entity.setCakeId(cakeId);
            entity = cakeRepository.save(entity);
            return CakeMapper.mapToRecord(entity);
        } else {
            throw new CakeNotFoundException();
        }
    }

    @Override
    @Transactional
    public void deleteExistingCake(Long cakeId) {
        Optional<CakeEntity> existingEntity = cakeRepository.findById(cakeId);
        if (existingEntity.isEmpty()) {
            throw new CakeNotFoundException();
        }
        cakeRepository.deleteById(cakeId);
    }

    @Override
    public Cake findByCakeId(Long cakeId) {
        Optional<CakeEntity> existingEntity = cakeRepository.findById(cakeId);
        if (existingEntity.isEmpty()) {
            throw new CakeNotFoundException();
        }
        return CakeMapper.mapToRecord(existingEntity.get());
    }
}
