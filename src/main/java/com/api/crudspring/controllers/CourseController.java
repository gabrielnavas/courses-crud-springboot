package com.api.crudspring.controllers;

import com.api.crudspring.models.Course;
import com.api.crudspring.repositories.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping
    public List<Course> list() {
        this.courseRepository.save(new Course("Angular", "front-end"));
        return courseRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Course course) {
        try {
            Course courseCreated = this.courseRepository.save(course);
            return ResponseEntity.status(HttpStatus.CREATED).body(courseCreated);
        }
        catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server error");
        }
    }
}
