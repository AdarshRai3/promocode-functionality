package com.promocode_functionality.promocode_functionality.services;

import com.promocode_functionality.promocode_functionality.dtos.CourseRequest;
import com.promocode_functionality.promocode_functionality.dtos.CourseResponse;
import com.promocode_functionality.promocode_functionality.entities.Courses;
import com.promocode_functionality.promocode_functionality.exceptions.CourseNotFoundException;
import com.promocode_functionality.promocode_functionality.repositories.CoursesRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoursesService {

    @Autowired
    private CoursesRespository coursesRepository;

    public CourseResponse createCourse(CourseRequest courseRequest) {
        Courses course = new Courses();
        course.setCourse_name(courseRequest.getCourse_name());
        course.setCourse_price(courseRequest.getCourse_price());
        course.setCourse_description(courseRequest.getCourse_description());

        Courses savedCourse = coursesRepository.save(course);
        return mapToResponse(savedCourse);
    }

    public List<CourseResponse> getAllCourses() {
        return coursesRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public CourseResponse getCourseById(Long id) {
        Courses course = coursesRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + id + " not found"));
        return mapToResponse(course);
    }

    public CourseResponse updateCourse(Long id, CourseRequest courseRequest) {
        Courses course = coursesRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + id + " not found"));

        course.setCourse_name(courseRequest.getCourse_name());
        course.setCourse_price(courseRequest.getCourse_price());
        course.setCourse_description(courseRequest.getCourse_description());

        Courses updatedCourse = coursesRepository.save(course);
        return mapToResponse(updatedCourse);
    }

    public void deleteCourse(Long id) {
        Courses course = coursesRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + id + " not found"));
        coursesRepository.delete(course);
    }

    private CourseResponse mapToResponse(Courses course) {
        return new CourseResponse(
                course.getCourse_id(),
                course.getCourse_name(),
                course.getCourse_price(),
                course.getCourse_description(),
                course.getCreated_at()
        );
    }
}