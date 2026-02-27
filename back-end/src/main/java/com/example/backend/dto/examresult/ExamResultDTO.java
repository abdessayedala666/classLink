package com.example.backend.dto.examresult;

import java.time.LocalDateTime;

public class ExamResultDTO {

    private Long id;
    private Double score;
    private String bucket;
    private String objectKey;
    private LocalDateTime submittedAt;
    private Long examId;
    private Long studentId;

    // Constructors
    public ExamResultDTO() {}

    public ExamResultDTO(Long id, Double score, String bucket, String objectKey, LocalDateTime submittedAt, Long examId, Long studentId) {
        this.id = id;
        this.score = score;
        this.bucket = bucket;
        this.objectKey = objectKey;
        this.submittedAt = submittedAt;
        this.examId = examId;
        this.studentId = studentId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
