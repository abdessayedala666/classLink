package com.example.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.services.EmailService;

@RestController
@RequestMapping("/test")
public class TestController {

    private final EmailService emailService;

    public TestController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/mail")
    public String sendTestMail() {
        emailService.sendEmail(
            "test@gmail.com",
            "Test Email",
            "Hello, this is a test email from Spring Boot + MailDev."
        );
        return "Email sent!";
    }
}