package com.promocode_functionality.promocode_functionality.controllers;

import com.promocode_functionality.promocode_functionality.dtos.CourseRequest;
import com.promocode_functionality.promocode_functionality.dtos.CourseResponse;
import com.promocode_functionality.promocode_functionality.services.CoursesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody CourseRequest courseRequest) {
        CourseResponse courseResponse = coursesService.createCourse(courseRequest);
        return new ResponseEntity<>(courseResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        List<CourseResponse> courses = coursesService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
        CourseResponse courseResponse = coursesService.getCourseById(id);
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequest courseRequest) {
        CourseResponse courseResponse = coursesService.updateCourse(id, courseRequest);
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        coursesService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
