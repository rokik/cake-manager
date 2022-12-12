package com.waracle.cakemanager.controller;

import com.waracle.cakemanager.contract.Cake;
import com.waracle.cakemanager.service.api.CakeManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cakes")
public class CakeController {
    private CakeManagerService cakeManagerService;

    protected CakeController(CakeManagerService service) {
        this.cakeManagerService = service;
    }


    @GetMapping
    ResponseEntity<List<Cake>> getAllCakes() {
        return new ResponseEntity<>(cakeManagerService.findAllCakes(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Cake> addNewCake(@RequestBody Cake cake) {
        return new ResponseEntity<>(cakeManagerService.addNewCake(cake), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    ResponseEntity<Cake> updateExistingCake(@PathVariable Long id, @RequestBody Cake cake) {
        return new ResponseEntity<>(cakeManagerService.updateExistingCake(id, cake), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    void deleteExistingCake(@PathVariable Long id) {
        cakeManagerService.deleteExistingCake(id);
    }

    @GetMapping("/{id}")
    ResponseEntity<Cake> getExistingCake(@PathVariable Long id) {
        return new ResponseEntity<>(cakeManagerService.findByCakeId(id), HttpStatus.OK);
    }
}
