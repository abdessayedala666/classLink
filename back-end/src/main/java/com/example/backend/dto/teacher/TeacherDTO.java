package com.example.backend.dto.teacher;

public class TeacherDTO {

    private Long id;
    private String subject;
    private Long userId;
    private Long schoolId;

    // Constructors
    public TeacherDTO() {}

    public TeacherDTO(Long id, String subject, Long userId, Long schoolId) {
        this.id = id;
        this.subject = subject;
        this.userId = userId;
        this.schoolId = schoolId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
}
