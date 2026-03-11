package com.example.backend.services;

import com.example.backend.dto.assignment.AssignmentDTO;
import com.example.backend.exceptions.UnauthorizedException;
import com.example.backend.models.Assignment;
import com.example.backend.models.Subject;
import com.example.backend.models.Teacher;
import com.example.backend.repository.AssignmentRepository;
import com.example.backend.repository.SubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final MinioService minioService ;
    private final AuthService authService ;
    private final SubjectRepository subjectRepository ;
    private final SpiceDBAuthorizationService spiceDBAuthorizationService ;


    public AssignmentService(AssignmentRepository assignmentRepository, 
        MinioService minioService, 
        AuthService authService, 
        SubjectRepository subjectRepository, 
        SpiceDBAuthorizationService spiceDBAuthorizationService) {
        this.assignmentRepository = assignmentRepository;
        this.minioService = minioService;
        this.authService = authService;
        this.subjectRepository = subjectRepository;
        this.spiceDBAuthorizationService = spiceDBAuthorizationService;
    }

    @Transactional
    public void uploadAssignmentFile(MultipartFile file, Long SubjectId , LocalDateTime deadline , String Description) throws Exception {
        Teacher teacher = authService.findCurrentTeacher();
        Subject subject = subjectRepository.findById(SubjectId).orElseThrow(
            () -> new RuntimeException("Subject not found")
        ) ;
        boolean spiceDBResult = spiceDBAuthorizationService.checkPermission(
            "teacher" , 
            teacher.getId().toString() ,
            "upload" ,  
            "subject" ,
            SubjectId.toString()
        ) ;
        if (!spiceDBResult){
            throw new UnauthorizedException("this teacher cant upload to such subject") ;
        }

        String bucketName = "assignments";
        String ObjectName = "subjects" + "/" + subject.getId() + "/" + file.getOriginalFilename();
        Assignment assignment = new Assignment() ;
        assignment.setSubject(subject);
        assignment.setDeadline(deadline);
        assignment.setFileName(file.getOriginalFilename());
        assignment.setDescription(Description);
        assignmentRepository.save(assignment);
        try {
            minioService.uploadFile(file, bucketName, ObjectName);
        } catch ( Exception e){
            throw e ;
        }
    }

    public List<AssignmentDTO> findAssignmentBySubjectId(Long subjectId) {
        List<Assignment> assignments = assignmentRepository.findBySubjectId(subjectId);
        return assignments.stream().map(assignment ->{
            AssignmentDTO dto = new AssignmentDTO() ;
            dto.setFileUrl(minioService.getFileUrl("assignments" , "subjects" + "/" + subjectId + "/" + assignment.getFileName()) ) ;
            dto.setSubjectId(subjectId);
            dto.setDeadline(assignment.getDeadline());
            dto.setDescription(assignment.getDescription());
            dto.setFileName(assignment.getFileName());
            return dto ;
        }).toList();
    }

    public Optional<Assignment> findById(Long id) {
        return assignmentRepository.findById(id);
    }


    public List<Assignment> findBySubjectId(Long subjectId) {
        return assignmentRepository.findBySubjectId(subjectId);
    }

    public Assignment save(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public void deleteById(Long id) {
        assignmentRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return assignmentRepository.existsById(id);
    }
}
