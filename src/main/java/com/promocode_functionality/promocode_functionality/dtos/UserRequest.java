package com.promocode_functionality.promocode_functionality.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "User name cannot be blank")
    @Size(min = 3, max = 50, message = "User name must be between 3 and 50 characters")
    private String user_name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String user_email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotNull(message = "Notification preferences must be specified")
    @Pattern(regexp = "^(email|phone)$", message = "Notification preferences must be either 'email' or 'phone'")
    private String notification_preferences;
}
