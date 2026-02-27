package com.example.backend.controller.AuthControllers;

import com.example.backend.dto.auth.ChangePasswordDTO;
import com.example.backend.dto.auth.LoginDTO;
import com.example.backend.dto.auth.LoginResponseDTO;
import com.example.backend.dto.auth.UserLoginDTO;
import com.example.backend.dto.auth.UserRegisterDTO;
import com.example.backend.security.CustomUserDetails;
import com.example.backend.services.AuthService;
import com.example.backend.services.SchoolAdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final SchoolAdminService schoolAdminService;

    public AuthController(AuthService authService, SchoolAdminService schoolAdminService) {
        this.authService = authService;
        this.schoolAdminService = schoolAdminService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        try {
            LoginResponseDTO response = authService.login(loginDTO, request);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid email or password"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request, HttpServletResponse response) {
        authService.logout(request, response);
        return ResponseEntity.ok(Map.of("message", "Logout successful"));
    }

    @PostMapping("/change-password-first-login")
    public ResponseEntity<?> changePasswordFirstLogin(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        try {
            authService.changePasswordFirstLogin(changePasswordDTO);
            return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated() 
                || authentication.getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Not authenticated"));
        }
        
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        LoginResponseDTO response = new LoginResponseDTO(
                userDetails.getEmail(),
                userDetails.getUsername(),
                userDetails.getRole().name(),
                userDetails.isFirstLogin()
        );
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("register/school-admins")
    public ResponseEntity<UserLoginDTO> registerSchoolAdmin(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        UserLoginDTO registeredUser = schoolAdminService.registerSchoolAdmin(userRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(registeredUser) ;
    }
}
