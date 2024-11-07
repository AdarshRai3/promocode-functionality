package com.promocode_functionality.promocode_functionality.controllers;

import com.promocode_functionality.promocode_functionality.dtos.PromocodeUsageResponse;
import com.promocode_functionality.promocode_functionality.services.PromocodeUsageService;
import com.promocode_functionality.promocode_functionality.entities.PromocodeUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promocodeUsage")
public class PromocodeUsageController {

    @Autowired
    private PromocodeUsageService promocodeUsageService;

    // Apply the promocode
    @PostMapping("/apply")
    @ResponseStatus(HttpStatus.CREATED)
    public PromocodeUsageResponse applyPromocode(@RequestParam Long userId,
                                                 @RequestParam Long courseId,
                                                 @RequestParam String code) {
        // Apply the promocode and retrieve the PromocodeUsage entity
        PromocodeUsage usage = promocodeUsageService.applyPromocode(code, userId, courseId);
        // Map the PromocodeUsage entity to a PromocodeUsageResponse DTO
        return mapToResponse(usage);
    }

    // Get all promocode usages
    @GetMapping
    public List<PromocodeUsageResponse> getAllPromocodeUsages() {
        return promocodeUsageService.getAllPromocodeUsages();
    }

    // Delete promocode usage by ID
    @DeleteMapping("/{usageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePromocodeUsageById(@PathVariable Long usageId) {
        promocodeUsageService.deletePromocodeUsageById(usageId);
    }

    // Helper method to map PromocodeUsage entity to PromocodeUsageResponse DTO
    private PromocodeUsageResponse mapToResponse(PromocodeUsage usage) {
        PromocodeUsageResponse response = new PromocodeUsageResponse();
        response.setUsageId(usage.getUsageId());
        response.setCode(usage.getCode());
        response.setUserId(usage.getUsersId().getUser_id());
        response.setCourseId(usage.getCoursesId().getCourse_id());
        response.setCourseName(usage.getCourse_name());
        response.setCoursePrice(usage.getCourse_price());
        response.setDiscountPrice(usage.getDiscountPrice());
        response.setCreatedAt(usage.getCreated_At());
        return response;
    }
}