package com.example.backend.dto.grade;

import com.example.backend.models.Grade;

public class GradeDTO {

    private Long id;
    private String name;
    private Long schoolId;

    // Constructors
    public GradeDTO() {}

    public GradeDTO(Grade grade) {
        this.id = grade.getId();
        this.name = grade.getName();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
