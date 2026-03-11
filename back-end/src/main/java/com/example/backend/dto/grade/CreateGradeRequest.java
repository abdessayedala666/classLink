package com.example.backend.dto.grade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateGradeRequest {
    
    @NotBlank(message = "Grade name is required")
    @Size(max = 100, message = "Grade name must be at most 100 characters")
    private String name;

    // Constructors
    public CreateGradeRequest() {}

    public CreateGradeRequest(String name) {
        this.name = name;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
