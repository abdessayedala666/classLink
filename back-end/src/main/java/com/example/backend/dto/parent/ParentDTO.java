package com.example.backend.dto.parent;

public class ParentDTO {

    private Long id;
    private Long userId;

    // Constructors
    public ParentDTO() {}

    public ParentDTO(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
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
}
