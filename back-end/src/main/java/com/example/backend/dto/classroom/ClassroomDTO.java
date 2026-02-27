package com.example.backend.dto.classroom;

public class ClassroomDTO {

    private Long id;
    private String name;
    private Long gradeId;

    // Constructors
    public ClassroomDTO() {}

    public ClassroomDTO(Long id, String name, Long gradeId) {
        this.id = id;
        this.name = name;
        this.gradeId = gradeId;
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

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }
}
