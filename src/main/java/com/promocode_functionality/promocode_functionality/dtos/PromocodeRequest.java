package com.promocode_functionality.promocode_functionality.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromocodeRequest {

    @NotNull(message = "Code cannot be null")
    @Pattern(regexp = "^[A-Z0-9]{15}$", message = "Code must be uppercase, numeric, and exactly 15 characters long")
    private String code;

    @NotNull(message = "Expiry date cannot be null")
    @Future(message = "Expiry date must be in the future")
    private LocalDateTime expiryDate;

    @NotNull(message = "Status cannot be null")
    @Pattern(regexp = "^(Active|Inactive)$", message = "Status must be either 'Active' or 'Inactive'")
    private String status;

    @NotNull(message = "Discount percent cannot be null")
    @Min(value = 0, message = "Discount percent must be at least 0")
    @Max(value = 100, message = "Discount percent must be at most 100")
    private Integer discountPercent;

    private String codeType = "Regular";

    private String generatedBy = "Admin";

    private Integer maxUsage = 0;
}