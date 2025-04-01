package com.example.student.models;

public class ShiftCategory {
    private int id;
    private String name;

    public ShiftCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
