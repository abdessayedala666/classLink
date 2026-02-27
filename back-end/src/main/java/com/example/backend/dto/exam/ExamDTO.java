package com.example.backend.dto.exam;

import java.time.LocalDateTime;

public class ExamDTO {

    private Long id;
    private String examType;
    private LocalDateTime createdAt;
    private Long classroomId;
    private Long subjectId;
    private Long teacherId;

    // Constructors
    public ExamDTO() {}

    public ExamDTO(Long id, String examType, LocalDateTime createdAt, Long classroomId, Long subjectId, Long teacherId) {
        this.id = id;
        this.examType = examType;
        this.createdAt = createdAt;
        this.classroomId = classroomId;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
