package com.example.backend.dto.student;

public class StudentDTO {

    private Long id;
    private Long userId;
    private Long classroomId;
    private Long parentId;
    private Long schoolId;

    // Constructors
    public StudentDTO() {}

    public StudentDTO(Long id, Long userId, Long classroomId, Long parentId, Long schoolId) {
        this.id = id;
        this.userId = userId;
        this.classroomId = classroomId;
        this.parentId = parentId;
        this.schoolId = schoolId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
}
