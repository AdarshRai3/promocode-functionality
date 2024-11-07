package com.promocode_functionality.promocode_functionality.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="promocode_usage_table")
public class PromocodeUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usage_id")
    private Long usageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id" , nullable = false)
    private Users usersId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="promocode_id", nullable=false)
    private Promocodes promocodesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="course_id",nullable =false)
    private Courses coursesId;

    @Column (name="code", nullable = false)
    private String code;

    @Column (name = "status" , nullable =false)
    private String status;

    @Column (name = "created_At",nullable = false)
    private LocalDateTime created_At;

    @Column(name = "course_name",nullable=false)
    private String course_name;

    @Column(name = "course_price",nullable = false)
    private BigDecimal course_price;

    @Column (name ="discount_price", nullable = false)
    private BigDecimal discountPrice;

    @PrePersist
    protected void onCreate(){
        created_At=LocalDateTime.now();
    }
}
