package com.example.backend.services;

import com.example.backend.dto.user.BulkUserCreationRequestDTO;
import com.example.backend.dto.user.BulkUserCreationResponseDTO;
import com.example.backend.dto.user.UserUpdateDTO;
import com.example.backend.exceptions.DuplicateResourceException;
import com.example.backend.exceptions.ResourceNotFoundException;
import com.example.backend.models.User;
import com.example.backend.models.enums.Role;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<BulkUserCreationResponseDTO> importTeachersFromExcel(BulkUserCreationRequestDTO requestDTO) {
        MultipartFile file = requestDTO.getFile() ;
        String originalFileName = file.getOriginalFilename() ;
        String Extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1) ;
        if (file == null || file.isEmpty() ||  !Extension.equals("xlsx")) {
            throw new IllegalArgumentException("File must be provided and should be an Excel file (.xlsx)");
        }
        Role role = requestDTO.getRole() ;
        if(role == null  || !(role == Role.TEACHER || role == Role.STUDENT) || role == Role.PARENT) {
            throw new IllegalArgumentException("Role must be either TEACHER or STUDENT");
        }
        List<BulkUserCreationResponseDTO> response = new ArrayList<>();
        


        return response ;
    }

    public User patchUser(Long id, UserUpdateDTO userUpdateDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        if (userUpdateDTO.getFullName() != null) {
            existingUser.setFullName(userUpdateDTO.getFullName());
        }

        if (userUpdateDTO.getEmail() != null) {
            String newEmail = userUpdateDTO.getEmail();
            if (!newEmail.equals(existingUser.getEmail()) && userRepository.existsByEmail(newEmail)) {
                throw new DuplicateResourceException("User", "email", newEmail);
            }
            existingUser.setEmail(newEmail);
        }

        if (userUpdateDTO.getBirthday() != null) {
            existingUser.setBirthday(userUpdateDTO.getBirthday());
        }

        if (userUpdateDTO.getGender() != null) {
            existingUser.setGender(userUpdateDTO.getGender());
        }

        return userRepository.save(existingUser);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
