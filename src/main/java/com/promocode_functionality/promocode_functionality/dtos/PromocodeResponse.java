package com.promocode_functionality.promocode_functionality.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromocodeResponse {

    private Long promocode_id;
    private String code;
    private LocalDateTime expiry_date;
    private String status;
    private Integer discountPercent;
    private String code_type;
    private String generated_By;
    private Integer maxUsageLimit;
    private LocalDateTime created_at;
}
