package com.example.backend.services;

import com.example.backend.dto.auth.UserLoginDTO;
import com.example.backend.dto.auth.UserRegisterDTO;
import com.example.backend.dto.teacher.TeacherDTO;
import com.example.backend.exceptions.DuplicateResourceException;
import com.example.backend.exceptions.EmailSendingException;
import com.example.backend.exceptions.ResourceNotFoundException;
import com.example.backend.models.Assignment;
import com.example.backend.models.School;
import com.example.backend.models.SchoolAdmin;
import com.example.backend.models.Subject;
import com.example.backend.models.Teacher;
import com.example.backend.models.User;
import com.example.backend.models.enums.Role;
import com.example.backend.repository.AssignmentRepository;
import com.example.backend.repository.SchoolAdminRepository;
import com.example.backend.repository.SchoolRepository;
import com.example.backend.repository.SubjectRepository;
import com.example.backend.repository.TeacherRepository;
import com.example.backend.repository.UserRepository;

import jakarta.transaction.Transactional;
import com.example.backend.utils.RandomPasswordGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final MinioService minioService ;
    private final AuthService authService ; 
    private final SubjectRepository subjectRepository ;
    private final AssignmentRepository assignmentRepository ;
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;
    private final RandomPasswordGenerator passwordGenerator;
    private final PasswordEncoder passwordEncoder;
    private final SchoolAdminRepository schoolAdminRepository;
    private final EmailService emailService ;
    private final SubjectService subjectService;

    public TeacherService(TeacherRepository teacherRepository, 
                            MinioService minioService ,  
                            AuthService authService ,
                            SubjectRepository subjectRepository,
                            AssignmentRepository assignmentRepository,
                            UserRepository userRepository,
                            SchoolRepository schoolRepository,
                            RandomPasswordGenerator passwordGenerator,
                            PasswordEncoder passwordEncoder,
                            SchoolAdminRepository schoolAdminRepository,
                            EmailService emailService ,
                            SubjectService subjectService
                            ) {
        this.teacherRepository = teacherRepository;
        this.minioService = minioService;
        this.authService = authService;
        this.subjectRepository = subjectRepository;
        this.assignmentRepository = assignmentRepository;
        this.userRepository = userRepository;
        this.schoolRepository = schoolRepository;
        this.passwordGenerator = passwordGenerator;
        this.passwordEncoder = passwordEncoder;
        this.schoolAdminRepository = schoolAdminRepository;
        this.emailService = emailService;
        this.subjectService = subjectService;
    }


    public UserLoginDTO registerTeacher(UserRegisterDTO dto) {
        // Check if email already exists
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("User", "email", dto.getEmail());
        }

        // Find the school
        School school = schoolRepository.findById(dto.getSchoolId())
                .orElseThrow(() -> new ResourceNotFoundException("School", "id", dto.getSchoolId()));

        // Generate a random password
        String rawPassword = passwordGenerator.generatePassword();

        // Create the User entity
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setBirthday(dto.getBirthday());
        user.setGender(dto.getGender());
        user.setRole(Role.TEACHER);
        user.setFirstLogin(true);
        user.setSchool(school);

        User savedUser = userRepository.save(user);

        Teacher teacher = new Teacher();
        teacher.setUser(savedUser) ;
        teacher.setSubjects(new ArrayList<>());
        teacher.setSchool(school);

        teacherRepository.save(teacher);
        try {
            emailService.sendEmail(
                user.getEmail(),
                "Welcome to ClassLink!",
                "Dear " + user.getFullName() + ",\n\nWelcome to ClassLink! Your account has been created successfully.\n\nYour login details are:\nEmail: " + user.getEmail() + "\nPassword: " + rawPassword + "\n\nPlease change your password upon first login."
            );
        } catch (Exception e) {
            throw new EmailSendingException("Failed to send welcome email", e);
        }
        ;
        return new UserLoginDTO(dto.getEmail(), rawPassword);
    }

    @Transactional
    public void assignSubjectToTeacher(Long teacherId , Long subjectId){
        subjectService.assignTeacherToSubject(teacherId, subjectId);
    }




    

    public List<TeacherDTO> getTeachersBySchool(){
        SchoolAdmin schoolAdmin = authService.findCurrentSchoolAdmin();
        List<Teacher> teachers = teacherRepository.findBySchoolId(schoolAdmin.getSchool().getId());
        return teachers.stream().map(TeacherDTO::new).toList() ;
        

    }
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    public Optional<Teacher> findByUserId(Long userId) {
        return teacherRepository.findByUserId(userId);
    }

    public List<Teacher> findBySchoolId(Long schoolId) {
        return teacherRepository.findBySchoolId(schoolId);
    }

    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return teacherRepository.existsById(id);
    }
}
