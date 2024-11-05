package com.promocode_functionality.promocode_functionality.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="promocodes_table")
public class Promocodes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "promocode_id")
    private Long promocode_id;

    @Column (name ="code", nullable=false , unique = true ,length = 10)
    private String code;

    @Column(name="expiry_date",nullable =false)
    private LocalDateTime expiry_date;

    @Column(name ="status", nullable=false)
    private String status;

    @Column (name ="discountPercent",nullable=false)
    private Integer discountPercent;

    @Column (name = "code_type")
    private String code_type;

    @Column (name ="generated_By")
    private String generated_By;

    @Column(name = "usage_count", nullable = false)
    private int usageCount = 0;

    @Column(name = "max_usage_limit")
    private Integer maxUsageLimit;

    @Column (name="created_At")
    private LocalDateTime created_at;

    @PrePersist
    protected void createOn(){
        created_at=LocalDateTime.now();
    }

    @OneToMany(mappedBy = "promocodes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PromocodeUsage> usageRecords;
}
