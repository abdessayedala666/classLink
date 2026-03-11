package com.example.backend.dto.teacher;

import java.util.List;

import com.example.backend.dto.subject.SubjectDTO;
import com.example.backend.models.Teacher;
import com.example.backend.models.enums.Gender;

import java.time.LocalDate;

public class TeacherDTO {

    private Long id;
    private List<SubjectDTO> subject;

    private String fullName ;
    private String email ;
    private LocalDate birthday ;
    private Gender gender ;

    // Constructors
    public TeacherDTO() {}

    public TeacherDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.fullName = teacher.getUser().getFullName() ;
        this.email = teacher.getUser().getEmail() ; 
        this.birthday = teacher.getUser().getBirthday() ;
        this.gender = teacher.getUser().getGender() ;
        this.subject = teacher.getSubjects().stream().map(SubjectDTO::new).toList() ;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SubjectDTO> getSubject() {
        return subject;
    }

    public void setSubject(List<SubjectDTO> subject) {
        this.subject = subject;
    }

   

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
