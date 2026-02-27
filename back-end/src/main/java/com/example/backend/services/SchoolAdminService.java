package com.example.backend.services;

import com.example.backend.dto.auth.UserLoginDTO;
import com.example.backend.dto.auth.UserRegisterDTO;
import com.example.backend.exceptions.DuplicateResourceException;
import com.example.backend.exceptions.EmailSendingException;
import com.example.backend.exceptions.ResourceNotFoundException;
import com.example.backend.exceptions.SpiceDBException;
import com.example.backend.models.School;
import com.example.backend.models.SchoolAdmin;
import com.example.backend.models.User;
import com.example.backend.models.enums.Role;
import com.example.backend.repository.SchoolAdminRepository;
import com.example.backend.repository.SchoolRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.utils.RandomPasswordGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolAdminService {

    private final SchoolAdminRepository schoolAdminRepository;
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;
    private final RandomPasswordGenerator passwordGenerator;
    private final PasswordEncoder passwordEncoder;
    private final SpiceDBAuthorizationService spiceDBAuthorizationService;
    private final EmailService emailService ;
    public SchoolAdminService(
            SchoolAdminRepository schoolAdminRepository,
            UserRepository userRepository,
            SchoolRepository schoolRepository,
            RandomPasswordGenerator passwordGenerator,
            PasswordEncoder passwordEncoder,
            SpiceDBAuthorizationService spiceDBAuthorizationService,
            EmailService emailService
    ) {
        this.schoolAdminRepository = schoolAdminRepository;
        this.userRepository = userRepository;
        this.schoolRepository = schoolRepository;
        this.passwordGenerator = passwordGenerator;
        this.passwordEncoder = passwordEncoder;
        this.spiceDBAuthorizationService = spiceDBAuthorizationService;
        this.emailService = emailService;
    }

    @Transactional(rollbackFor = Exception.class)
    public UserLoginDTO registerSchoolAdmin(UserRegisterDTO dto) {
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
        user.setRole(Role.SCHOOL_ADMIN);
        user.setFirstLogin(true);
        user.setSchool(school);

        User savedUser = userRepository.save(user);

        // Create the SchoolAdmin entity
        SchoolAdmin schoolAdmin = new SchoolAdmin();
        schoolAdmin.setUser(savedUser);
        schoolAdmin.setSchool(school);

        schoolAdminRepository.save(schoolAdmin);

        // Register relationship in SpiceDB - if this fails, rollback everything
        try {
            boolean spiceDBSuccess = spiceDBAuthorizationService.makeRelationship(
                    "schooladmin",
                    savedUser.getId().toString(),
                    "manager",
                    "school",
                    school.getId().toString()
            );
            
            if (!spiceDBSuccess) {
                throw new SpiceDBException("Failed to create relationship in SpiceDB");
            }
        } catch (Exception e) {
            throw new SpiceDBException("SpiceDB registration failed: " + e.getMessage());
        }

        // Return the credentials

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

    public List<SchoolAdmin> findAll() {
        return schoolAdminRepository.findAll();
    }

    public Optional<SchoolAdmin> findById(Long id) {
        return schoolAdminRepository.findById(id);
    }

    public Optional<SchoolAdmin> findByUserId(Long userId) {
        return schoolAdminRepository.findByUserId(userId);
    }

    public List<SchoolAdmin> findBySchoolId(Long schoolId) {
        return schoolAdminRepository.findBySchoolId(schoolId);
    }

    public SchoolAdmin save(SchoolAdmin schoolAdmin) {
        return schoolAdminRepository.save(schoolAdmin);
    }

    public void deleteById(Long id) {
        schoolAdminRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return schoolAdminRepository.existsById(id);
    }
}
