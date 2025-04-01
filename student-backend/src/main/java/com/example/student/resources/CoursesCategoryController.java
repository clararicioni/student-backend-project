package com.example.student.resources;

import java.util.Arrays;
import java.util.List;

import com.example.student.models.CoursesCategory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class CoursesCategoryController {

    private List<CoursesCategory> categories = Arrays.asList(new CoursesCategory(1, "Angular"),
            new CoursesCategory(2, "MySQL"),
            new CoursesCategory(3, "C#"),
            new CoursesCategory(4, "React"));

    @GetMapping("courses")
    public List<CoursesCategory> getCategories() {
        return categories;
    }

    @GetMapping("courses/{id}")
    public ResponseEntity<CoursesCategory> getCategory(@PathVariable int id) {
        CoursesCategory cat = categories.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
        return ResponseEntity.ok(cat);
    }

}
