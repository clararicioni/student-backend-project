package com.example.student.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.student.models.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController 
@CrossOrigin 
public class StudentController {

    private List<Student> students = new ArrayList<>();

    @PostMapping("students")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        student.setRa(students.size() + 1);
        students.add(student);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{ra}")
                .buildAndExpand(student.getRa())
                .toUri();

        return ResponseEntity.created(location).body(student);
    }

    @GetMapping("students/{ra}")
    public ResponseEntity<Student> getStudent(@PathVariable int ra) {
        Student stud = students.stream()
                .filter(p -> p.getRa() == ra)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        return ResponseEntity.ok(stud);
    }

    @GetMapping("students")
    public List<Student> getStudent() {
        return students;
    }
}
