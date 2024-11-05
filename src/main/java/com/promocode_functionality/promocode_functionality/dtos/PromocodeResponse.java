package com.promocode_functionality.promocode_functionality.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PromocodeResponse {

    private Long promocodeId;
    private String code;
    private LocalDateTime expiryDate;
    private String status;
    private Integer discountPercent;
    private String codeType;
    private String generatedBy;
    private Integer maxUsage;
    private LocalDateTime createdAt;
}
