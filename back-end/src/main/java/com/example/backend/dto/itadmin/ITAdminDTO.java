package com.example.backend.dto.itadmin;

public class ITAdminDTO {

    private Long id;
    private Long userId;
    private Long schoolId;

    // Constructors
    public ITAdminDTO() {}

    public ITAdminDTO(Long id, Long userId, Long schoolId) {
        this.id = id;
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
