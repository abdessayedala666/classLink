package com.example.backend.dto.homework;

import java.time.LocalDateTime;

public class HomeworkDTO {

    private Long id;
    private String fileName;
    private Integer score;
    private LocalDateTime submittedAt;
    private Long assignmentId;
    private Long studentId;

    // Constructors
    public HomeworkDTO() {}

    public HomeworkDTO(Long id, String fileName, Integer score, LocalDateTime submittedAt, Long assignmentId, Long studentId) {
        this.id = id;
        this.fileName = fileName;
        this.score = score;
        this.submittedAt = submittedAt;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
