package com.promocode_functionality.promocode_functionality.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Long course_id;
    private String course_name;
    private BigDecimal course_price;
    private String course_description;
    private LocalDateTime created_at;
}
