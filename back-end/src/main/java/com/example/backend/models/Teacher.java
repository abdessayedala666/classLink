package com.example.backend.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToMany
    @JoinTable(
        name = "teacher_classrooms",
        joinColumns = @JoinColumn(name = "teacher_id"),
        inverseJoinColumns = @JoinColumn(name = "classroom_id")
    )
    private List<Classroom> classrooms;

    @OneToMany(mappedBy = "teacher" , cascade = CascadeType.ALL)
    private List<Subject> subjects;

    @OneToMany(mappedBy = "teacher")
    private List<Exam> exams;

    // Constructors
    public Teacher() {}



    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
    public void addSubject(Subject subject) {
        this.subjects.add(subject) ;
        subject.setTeacher(this);
    }


    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
