package com.promocode_functionality.promocode_functionality.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "courses_table")
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "course_id")
    private Long course_id;

    @Column (name= "course_name",nullable=false)
    private String course_name;

    @Column(name="course_price",nullable = false)
    private BigDecimal course_price;

    @Column(name = "column_description",nullable = false)
    private String course_description;

    @Column (name="created_At")
    private LocalDateTime created_at;

    @OneToMany(mappedBy = "coursesId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PromocodeUsage> promocodeUsages;

    @PrePersist
    protected void createOn(){
        created_at=LocalDateTime.now();
    }

}
