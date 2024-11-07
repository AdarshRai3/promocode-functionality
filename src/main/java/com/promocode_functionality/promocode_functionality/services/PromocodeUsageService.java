package com.promocode_functionality.promocode_functionality.services;


import com.promocode_functionality.promocode_functionality.dtos.PromocodeUsageResponse;
import com.promocode_functionality.promocode_functionality.entities.Courses;
import com.promocode_functionality.promocode_functionality.entities.PromocodeUsage;
import com.promocode_functionality.promocode_functionality.entities.Promocodes;
import com.promocode_functionality.promocode_functionality.entities.Users;
import com.promocode_functionality.promocode_functionality.exceptions.*;
import com.promocode_functionality.promocode_functionality.repositories.CoursesRespository;
import com.promocode_functionality.promocode_functionality.repositories.PromocodeUsageRepository;
import com.promocode_functionality.promocode_functionality.repositories.PromocodesRepository;
import com.promocode_functionality.promocode_functionality.repositories.UsersRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromocodeUsageService {

    @Autowired
    private PromocodesRepository promocodesRepository;

    @Autowired
    private UsersRespository usersRepository;

    @Autowired
    private CoursesRespository coursesRepository;

    @Autowired
    private PromocodeUsageRepository promocodeUsageRepository;

    public PromocodeUsage applyPromocode(String code, Long userId, Long courseId) throws InvalidPromocodeException, PromocodeExpiredException, PromocodeInactiveException {

        // Step 1: Retrieve promocode, user, and course details
        Promocodes promocode = promocodesRepository.findByCode(code)
                .orElseThrow(() -> new InvalidPromocodeException("Promo code not found"));

        // Step 2: Validate promocode status and expiry date
        validatePromocode(promocode);

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        // Step 3: Calculate the discount price
        BigDecimal discountPrice = calculateDiscountedPrice(course.getCourse_price(), promocode.getDiscountPercent());

        // Step 4: Create and save the new PromocodeUsage record
        PromocodeUsage usage = new PromocodeUsage();
        usage.setCode(promocode.getCode());
        usage.setCourse_name(course.getCourse_name());
        usage.setCourse_price(course.getCourse_price());
        usage.setCoursesId(course);
        usage.setDiscountPrice(discountPrice);
        usage.setStatus("Applied");
        usage.setPromocodesId(promocode);
        usage.setUsersId(user);
        usage.setCreated_At(LocalDateTime.now());

        // Save the usage record
        return promocodeUsageRepository.save(usage);
    }

    // Helper method to validate the promocode
    private void validatePromocode(Promocodes promocode) throws PromocodeExpiredException, PromocodeInactiveException {
        if ("Inactive".equalsIgnoreCase(promocode.getStatus())) {
            throw new PromocodeInactiveException("Promo code is inactive");
        }

        if (promocode.getExpiry_date().isBefore(LocalDateTime.now())) {
            throw new PromocodeExpiredException("Promo code has expired");
        }
    }

    // Helper method to calculate the discounted price
    private BigDecimal calculateDiscountedPrice(BigDecimal coursePrice, Integer discountPercent) {
        BigDecimal discountAmount = BigDecimal.valueOf(discountPercent).divide(BigDecimal.valueOf(100)); // Convert discountPercent to a BigDecimal
        BigDecimal discountedPrice = coursePrice.subtract(coursePrice.multiply(discountAmount)); // Calculate discounted price
        return discountedPrice.setScale(2, RoundingMode.HALF_UP);
    }


    public List<PromocodeUsageResponse> getAllPromocodeUsages() {
        return promocodeUsageRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void deletePromocodeUsageById(Long usageId) {
        if (!promocodeUsageRepository.existsById(usageId)) {
            throw new RuntimeException("Promocode usage not found with ID " + usageId);
        }
        promocodeUsageRepository.deleteById(usageId);
    }
    private PromocodeUsageResponse mapToResponse(PromocodeUsage usage) {
        PromocodeUsageResponse response = new PromocodeUsageResponse();
        response.setUsageId(usage.getUsageId());
        response.setCode(usage.getPromocodesId().getCode());
        response.setStatus(usage.getStatus());
        response.setUserId(usage.getUsersId().getUser_id());
        response.setCourseId(usage.getCoursesId().getCourse_id());
        response.setCourseName(usage.getCourse_name());
        response.setCoursePrice(usage.getCourse_price());
        response.setDiscountPrice(usage.getDiscountPrice());
        response.setCreatedAt(usage.getCreated_At());
        return response;
    }
}
