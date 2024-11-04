package com.promocode_functionality.promocode_functionality.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class CourseRequest {

    @NotBlank(message = "Course name cannot be blank")
    private String course_name;

    @NotNull(message = "Course price cannot be null")
    @Positive(message = "Course price must be positive")
    private BigDecimal course_price;

    @NotBlank(message = "Course description cannot be empty")
    private String course_description;

}
