package com.example.backend.dto.assignment;

import java.time.LocalDateTime;

public class AssignmentDTO {

    private Long id;
    private Long classroomId;
    private Long teacherId;
    private Long subjectId;
    private String fileName;
    private String description;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;

    // Constructors
    public AssignmentDTO() {}

    public AssignmentDTO(Long id, Long classroomId, Long teacherId, Long subjectId, String fileName, String description, LocalDateTime deadline, LocalDateTime createdAt) {
        this.id = id;
        this.classroomId = classroomId;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
        this.fileName = fileName;
        this.description = description;
        this.deadline = deadline;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
