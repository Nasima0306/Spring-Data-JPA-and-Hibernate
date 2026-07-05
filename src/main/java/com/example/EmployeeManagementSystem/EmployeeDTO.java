package com.example.EmployeeManagementSystem;

public class EmployeeDTO {

    private String name;
    private String email;

    public EmployeeDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}