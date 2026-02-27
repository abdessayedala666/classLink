package com.example.backend.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.backend.security.CustomUserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FirstLoginFilter extends OncePerRequestFilter {
    
    private static final String CHANGE_PASSWORD_ENDPOINT = "/api/auth/change-password-first-login";
    
    private static final List<String> EXCLUDED_PATHS = Arrays.asList(
        "/api/auth/login",
        "/api/auth/logout",
        "/api/auth/change-password-first-login",
        "/api/auth/register"
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return EXCLUDED_PATHS.stream().anyMatch(path::startsWith);
    }

    @Override 
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, 
                                    FilterChain filterChain
    ) throws ServletException, IOException {
        // Get the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Check if the user is authenticated
        if (authentication == null || 
            !authentication.isAuthenticated() || 
            authentication instanceof AnonymousAuthenticationToken) {
            filterChain.doFilter(request, response);
            return;
        }
        
        // Get the principal (user details) from the authentication object
        Object principal = authentication.getPrincipal();
        
        if (principal instanceof CustomUserDetails userDetails) {
            if (userDetails.isFirstLogin()) {
                // Gets the path requested by the user
                String requestPath = request.getRequestURI();
                
                // Check if the request path is different from the change password endpoint
                if (!requestPath.equals(CHANGE_PASSWORD_ENDPOINT)) {
                    // Return forbidden with JSON error
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType("application/json");
                    response.getWriter().write("""
                            {"error": "password_change_required",
                             "message": "First login - password change required"}
                            """);
                    return;
                }
            }
        }
        
        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
