package com.example.backend.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "schools")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "school")
    private List<Grade> grades;

    @OneToMany(mappedBy = "school")
    private List<User> users;

    @OneToMany(mappedBy = "school")
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "school")
    private List<Student> students;

    @OneToMany(mappedBy = "school")
    private List<ITAdmin> itAdmins;

    @OneToMany(mappedBy = "school")
    private List<SchoolAdmin> schoolAdmins;

    // Constructors
    public School() {}

    public School(String name, String address) {
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<ITAdmin> getItAdmins() {
        return itAdmins;
    }

    public void setItAdmins(List<ITAdmin> itAdmins) {
        this.itAdmins = itAdmins;
    }

    public List<SchoolAdmin> getSchoolAdmins() {
        return schoolAdmins;
    }

    public void setSchoolAdmins(List<SchoolAdmin> schoolAdmins) {
        this.schoolAdmins = schoolAdmins;
    }
}
