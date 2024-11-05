package com.promocode_functionality.promocode_functionality.controllers;

import com.promocode_functionality.promocode_functionality.dtos.PromocodeRequest;
import com.promocode_functionality.promocode_functionality.dtos.PromocodeResponse;
import com.promocode_functionality.promocode_functionality.services.PromocodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promocodes")
public class PromocodesController {

    @Autowired
    private PromocodesService promocodesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PromocodeResponse createPromocode(@RequestBody PromocodeRequest request) {
        return promocodesService.createPromocode(request);
    }

    @GetMapping("/{id}")
    public PromocodeResponse getPromocodeById(@PathVariable Long id) {
        return promocodesService.getPromocodeById(id);
    }

    @GetMapping
    public List<PromocodeResponse> getAllPromocodes() {
        return promocodesService.getAllPromocodes();
    }

    @PutMapping("/{id}")
    public PromocodeResponse updatePromocode(@PathVariable Long id, @RequestBody PromocodeRequest request) {
        return promocodesService.updatePromocode(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePromocode(@PathVariable Long id) {
        promocodesService.deletePromocode(id);
    }

    @GetMapping("/validate/{code}")
    public String validatePromocode(@PathVariable String code) {
        return promocodesService.validatePromocode(code);
    }

}

