package com.example.backend.dto.subject;

import com.example.backend.models.Subject;

public class SubjectDTO {

    private Long id;
    private String name;


    // Constructors
    public SubjectDTO() {}

    public SubjectDTO(Subject subject ) {
        this.id = subject.getId();
        this.name = subject.getName();
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
