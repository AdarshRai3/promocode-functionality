package com.promocode_functionality.promocode_functionality.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PromocodeUsageResponse{

    private Long usageId;
    private String code;
    private String status;
    private Long userId;
    private Long courseId;
    private String courseName;
    private BigDecimal coursePrice;
    private BigDecimal discountPrice;
    private LocalDateTime createdAt;
}