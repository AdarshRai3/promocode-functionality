package com.promocode_functionality.promocode_functionality.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long user_id;
    private String user_name;
    private String user_email;
    private String notification_preferences;
}