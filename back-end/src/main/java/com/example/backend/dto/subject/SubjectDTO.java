package com.example.backend.dto.subject;

public class SubjectDTO {

    private Long id;
    private String name;
    private Long classroomId;
    private Long teacherId;

    // Constructors
    public SubjectDTO() {}

    public SubjectDTO(Long id, String name, Long classroomId, Long teacherId) {
        this.id = id;
        this.name = name;
        this.classroomId = classroomId;
        this.teacherId = teacherId;
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
}
