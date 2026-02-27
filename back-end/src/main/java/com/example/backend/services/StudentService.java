package com.example.backend.services;

import com.example.backend.models.Student;
import com.example.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> findByUserId(Long userId) {
        return studentRepository.findByUserId(userId);
    }

    public List<Student> findByClassroomId(Long classroomId) {
        return studentRepository.findByClassroomId(classroomId);
    }

    public List<Student> findBySchoolId(Long schoolId) {
        return studentRepository.findBySchoolId(schoolId);
    }

    public List<Student> findByParentId(Long parentId) {
        return studentRepository.findByParentId(parentId);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }
}
