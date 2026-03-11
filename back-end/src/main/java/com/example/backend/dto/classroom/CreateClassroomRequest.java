package com.example.backend.dto.classroom;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateClassroomRequest {
    
    @NotBlank(message = "Classroom name is required")
    @Size(max = 100, message = "Classroom name must be at most 100 characters")
    private String classroomName;

    // Optional: controller can inject this from the route path variable.
    private Long gradeId;

    // Constructors
    public CreateClassroomRequest() {}

    public CreateClassroomRequest(String classroomName) {
        this.classroomName = classroomName;
    }

    public CreateClassroomRequest(String classroomName, Long gradeId) {
        this.classroomName = classroomName;
        this.gradeId = gradeId;
    }

    // Getters and Setters
    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }
}
