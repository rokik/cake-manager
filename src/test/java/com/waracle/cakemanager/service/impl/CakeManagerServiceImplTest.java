package com.waracle.cakemanager.service.impl;

import com.waracle.cakemanager.contract.Cake;
import com.waracle.cakemanager.domain.CakeEntity;
import com.waracle.cakemanager.exception.CakeAlreadyExistsException;
import com.waracle.cakemanager.exception.CakeNotFoundException;
import com.waracle.cakemanager.repository.api.CakeRepository;
import com.waracle.cakemanager.repository.api.EmployeeRepository;
import com.waracle.cakemanager.service.mapper.CakeMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CakeManagerServiceImplTest {
    @Mock
    private CakeRepository cakeRepository;
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private CakeManagerServiceImpl cakeManagerService;

    private Cake newCake;
    private Cake existingCake;
    private CakeEntity newCakeEntity;
    private CakeEntity existingCakeEntity;


    @BeforeEach
    public void setup() {
        newCake = new Cake("title", "some description", "http://url");
        existingCake = new Cake(1L, "some title", "some description");
        newCakeEntity = new CakeEntity();
        newCakeEntity.setTitle("title");
        existingCakeEntity = new CakeEntity();
        existingCakeEntity.setTitle("some title");
        existingCakeEntity.setCakeId(1L);
    }

    @Test
    public void testFindAllCakesReturningOne() {
        BDDMockito.given(cakeRepository.findAll())
                .willReturn(Arrays.asList(CakeMapper.mapToCakeEntity(existingCake)));
        List<Cake> allCakes = cakeManagerService.findAllCakes();
        Assertions.assertNotNull(allCakes);
        Assertions.assertTrue(allCakes.size() == 1);
    }

    @Test
    public void testFindAllCakesReturningZero() {
        List<Cake> allCakes = cakeManagerService.findAllCakes();
        Assertions.assertNotNull(allCakes);
        Assertions.assertTrue(allCakes.size() == 0);
    }

    @Test
    public void testDeleteExistingCakeSuccess() {
        BDDMockito.when(cakeRepository.findById(1l)).thenReturn(Optional.of(CakeMapper.mapToCakeEntity(existingCake)));
        cakeManagerService.deleteExistingCake(1l);
    }

    @Test
    public void testDeleteExistingCakeFailure() {
        BDDMockito.when(cakeRepository.findById(1l)).thenReturn(Optional.empty());

        Assertions.assertThrows(CakeNotFoundException.class, () -> {
            cakeManagerService.deleteExistingCake(1l);
        });
    }

    @Test
    public void testAddNewCakeSuccess() {
        BDDMockito.when(cakeRepository.existsByTitle(Mockito.anyString())).thenReturn(Boolean.FALSE);
        BDDMockito.when(cakeRepository.save(Mockito.any(CakeEntity.class))).thenReturn(existingCakeEntity);

        Cake cake = cakeManagerService.addNewCake(newCake);

        Assertions.assertNotNull(cake);
        Assertions.assertTrue(cake.cakeId() == existingCakeEntity.getCakeId());
    }

    @Test
    public void testAddNewCakeFailure() {
        BDDMockito.when(cakeRepository.existsByTitle(Mockito.anyString())).thenReturn(Boolean.TRUE);

        Assertions.assertThrows(CakeAlreadyExistsException.class, () -> {
            cakeManagerService.addNewCake(newCake);
        });
    }
}
