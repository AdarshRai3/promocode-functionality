package com.promocode_functionality.promocode_functionality.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "users_table")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long user_id;

    @Column(name="user_name", nullable = false,unique = true,length = 50)
    private String user_name;

    @Column(name="user_email",nullable = false,unique = true,length = 50)
    private String user_email;

    @Column(name="password",nullable = false)
    private String password;

    @Column (name="notification_preference")
    private String notification_preferences;

    @Column (name="created_At")
    private LocalDateTime created_at;

    @OneToMany(mappedBy = "usersId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PromocodeUsage> promocodeUsages;

    @PrePersist
    protected void createOn(){
        created_at=LocalDateTime.now();
    }
}
