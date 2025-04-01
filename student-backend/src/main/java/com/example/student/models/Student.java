package com.example.student.models;

public class Student {
    private int ra, idCourse, idShift;
    private String name, email, phone;

    public Student(int ra, int idCourse, int idShift, String name, String email, String phone) {
        this.ra = ra;
        this.idCourse = idCourse;
        this.idShift = idShift;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public int getRa() {
        return ra;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public int getIdShift() {
        return idShift;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
