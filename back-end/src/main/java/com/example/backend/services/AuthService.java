package com.example.backend.services;

import com.example.backend.dto.auth.ChangePasswordDTO;
import com.example.backend.dto.auth.LoginDTO;
import com.example.backend.dto.auth.LoginResponseDTO;
import com.example.backend.exceptions.UserNotFoundException;
import com.example.backend.models.School;
import com.example.backend.models.SchoolAdmin;
import com.example.backend.models.Teacher;
import com.example.backend.models.User;
import com.example.backend.repository.SchoolAdminRepository;
import com.example.backend.repository.TeacherRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.security.CustomUserDetails;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository ;
    private final SchoolAdminRepository schoolAdminRepository ;

    public AuthService(AuthenticationManager authenticationManager, 
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       TeacherRepository teacherRepository,
                       SchoolAdminRepository schoolAdminRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.teacherRepository = teacherRepository;
        this.schoolAdminRepository = schoolAdminRepository;
    }

    public LoginResponseDTO login(LoginDTO loginDTO, HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        // Set authentication in SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Create session and store SecurityContext
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        // Get CustomUserDetails from authentication principal
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return new LoginResponseDTO(
                userDetails.getEmail(),
                userDetails.getUsername(),
                userDetails.getRole().name(),
                userDetails.isFirstLogin()
        );
    }

    public void changePasswordFirstLogin(ChangePasswordDTO changePasswordDTO) {
        // Get current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        
        // Verify old password
        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), userDetails.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }
        
        // Get the user from database
        User user = userRepository.findByEmail(userDetails.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        // Update password and set firstLogin to false
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        user.setFirstLogin(false);
        
        userRepository.save(user);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        
        // Clear the session cookie by setting max age to 0
        Cookie sessionCookie = new Cookie("JSESSIONID", null);
        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(true);
        sessionCookie.setMaxAge(0);
        response.addCookie(sessionCookie);
    }
    public Teacher findCurrentTeacher() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication() ;
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal() ;
        String email = userDetails.getEmail() ;
        Teacher teacher = teacherRepository.findByUserEmail(email).orElseThrow(
            () -> new UserNotFoundException("User not found")
        );
        return teacher ;
    }
    public SchoolAdmin findCurrentSchoolAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication() ;
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal() ;
        String email = userDetails.getEmail() ;
        SchoolAdmin schoolAdmin = schoolAdminRepository.findByUserEmail(email).orElseThrow(
            () -> new UserNotFoundException("User not found")
        );
        return schoolAdmin ;
}}
