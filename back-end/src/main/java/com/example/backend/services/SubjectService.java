package com.example.backend.services;

import com.example.backend.dto.subject.CreateSubjectDTO;
import com.example.backend.dto.subject.SubjectDTO;
import com.example.backend.exceptions.EmailSendingException;
import com.example.backend.exceptions.ResourceNotFoundException;
import com.example.backend.exceptions.SpiceDBException;
import com.example.backend.models.Classroom;
import com.example.backend.models.School;
import com.example.backend.models.SchoolAdmin;
import com.example.backend.models.Subject;
import com.example.backend.models.Teacher;
import com.example.backend.repository.ClassroomRepository;
import com.example.backend.repository.SubjectRepository;
import com.example.backend.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final AuthService authService ;
    private final SpiceDBAuthorizationService spiceDBAuthorizationService ;
    private final ClassroomRepository classroomRepository ;
    private final TeacherRepository teacherRepository;
    private final EmailService emailService;

    public SubjectService(SubjectRepository subjectRepository,
                            AuthService authService ,
                            SpiceDBAuthorizationService spiceDBAuthorizationService   ,
                            ClassroomRepository classroomRepository,
                            TeacherRepository teacherRepository,
                            EmailService emailService
    ) {
        this.subjectRepository = subjectRepository;
        this.authService = authService ;
        this.spiceDBAuthorizationService = spiceDBAuthorizationService ;
        this.classroomRepository = classroomRepository ;
        this.teacherRepository = teacherRepository;
        this.emailService = emailService;
    }
    
    @Transactional
    public void createSubject( CreateSubjectDTO request){
        Long classroomId = request.getClassroomId() ;
        if (classroomId == null) {
            throw new IllegalArgumentException("classroomId is required");
        }
        SchoolAdmin schoolAdmin = authService.findCurrentSchoolAdmin() ;
        boolean SpiceDBResult = spiceDBAuthorizationService.checkPermission(
            "schooladmin" ,
            schoolAdmin.getId().toString() ,
            "manage" ,
            "classroom" ,
            classroomId.toString()
        ) ;
        if(!SpiceDBResult){
            throw new SecurityException("You do not have permission to manage subjects for this classroom");
        }
        Classroom classroom = classroomRepository.findById(classroomId)
            .orElseThrow(() -> new IllegalArgumentException("Classroom not found with id: " + classroomId)) ;
        boolean existingSubject = subjectRepository.existsByNameAndClassroomId(request.getSubjectName().trim(), classroomId) ;
        if (existingSubject) {
            throw new IllegalArgumentException("Subject already exists for this classroom");
        }
        Subject subject = new Subject() ;
        subject.setName(request.getSubjectName().trim());
        subject.setClassroom(classroom);
        subjectRepository.save(subject);
        try {
            spiceDBAuthorizationService.makeRelationship(
                "classroom" ,
                classroom.getId().toString() ,
                "classroom_owner" ,
                "subject" ,
                subject.getId().toString()
            ) ;
        } catch (Exception e) {
            throw new SpiceDBException("Failed to create relationship in SpiceDB: " + e.getMessage());
        }

    }

    public List<SubjectDTO> getSubjectsByClassroomId(Long classroomId){
        List<Subject> subjects = subjectRepository.findByClassroomId(classroomId) ;
        return subjects.stream().map(SubjectDTO::new).toList() ;
        
    }

    @Transactional
    public void assignTeacherToSubject(Long teacherId, Long subjectId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(
            () -> new ResourceNotFoundException("teacher not found")
        );
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(
            () -> new ResourceNotFoundException("subject not found")
        );

        // Check if already assigned
        if (subject.getTeacher() != null && subject.getTeacher().equals(teacher)) {
            return; // Already assigned, skip
        }

        // Initialize subjects list if null
        if (teacher.getSubjects() == null) {
            teacher.setSubjects(new ArrayList<>());
        }
        
        // Add subject if not already in list (this sets both sides)
        if (!teacher.getSubjects().contains(subject)) {
            teacher.addSubject(subject);
        }

        // Save only the owning side (Subject has the foreign key)
        subjectRepository.save(subject);

        try {
            boolean spiceDBSuccess = spiceDBAuthorizationService.makeRelationship(
                "teacher",
                teacher.getId().toString(),
                "teacher",
                "subject",
                subject.getId().toString()
            );
            if (!spiceDBSuccess) {
                throw new SpiceDBException("Failed to create relationship in SpiceDB");
            }
        } catch (Exception e) {
            throw new SpiceDBException("SpiceDB registration failed: " + e.getMessage());
        }

        try {
            emailService.sendEmail(
                teacher.getUser().getEmail(),
                "New Subject Assigned : " + subject.getName() + " classroom :  " + subject.getClassroom(),
                "Dear " + teacher.getUser().getFullName() + ",\n\nYou have been assigned to teach the subject: " + subject.getName() + " in classroom: " + subject.getClassroom() + ".\n\nPlease check your dashboard for more details."
            );
        } catch (Exception e) {
            throw new EmailSendingException("Failed to send email notification", e);
        }
    }



    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> findById(Long id) {
        return subjectRepository.findById(id);
    }

    public List<Subject> findByClassroomId(Long classroomId) {
        return subjectRepository.findByClassroomId(classroomId);
    }

    public Optional<Subject> findByTeacherId(Long teacherId) {
        return subjectRepository.findByTeacherId(teacherId);
    }

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public void deleteById(Long id) {
        subjectRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return subjectRepository.existsById(id);
    }
}
