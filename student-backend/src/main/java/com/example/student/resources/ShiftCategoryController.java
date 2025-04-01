package com.example.student.resources;

import java.util.Arrays;
import java.util.List;

import com.example.student.models.ShiftCategory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class ShiftCategoryController {

    private List<ShiftCategory> shift = Arrays.asList(new ShiftCategory(1, "Manhã"),
            new ShiftCategory(2, "Tarde"),
            new ShiftCategory(3, "Noite"));

    @GetMapping("shifts")
    public List<ShiftCategory> getShiftCategories() {
        return shift;
    }

    @GetMapping("shifts/{id}")
    public ResponseEntity<ShiftCategory> getShiftCategories(@PathVariable int id) {
        ShiftCategory scat = shift.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
        return ResponseEntity.ok(scat);
    }

}
