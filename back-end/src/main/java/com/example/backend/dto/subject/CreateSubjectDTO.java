package com.example.backend.dto.subject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateSubjectDTO {
    
    @NotBlank(message = "Subject name is required")
    @Size(max = 100, message = "Subject name must be at most 100 characters")
    private String subjectName;

    // Optional: controller can inject this from the route path variable.
    private Long classroomId;

    // Constructors
    public CreateSubjectDTO() {}

    public CreateSubjectDTO(String subjectName) {
        this.subjectName = subjectName;
    }

    public CreateSubjectDTO(String subjectName, Long classroomId) {
        this.subjectName = subjectName;
        this.classroomId = classroomId;
    }

    // Getters and Setters
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }
}
