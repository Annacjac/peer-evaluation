package edu.tcu.cs.peerevalbackend.student;

import edu.tcu.cs.peerevalbackend.admin.Email.EmailService;
import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmailService emailService;

    public void inviteStudents(List<String> emails){
        List<Student> studentsToInvite = studentRepository.findAllByEmailIn(emails)
                .orElseThrow(() -> new ObjectNotFoundException("emails", String.valueOf(emails)));
        studentsToInvite.forEach(student -> {
            emailService.sendInvitationEmail((student.getEmail()));
        });
    }
}
