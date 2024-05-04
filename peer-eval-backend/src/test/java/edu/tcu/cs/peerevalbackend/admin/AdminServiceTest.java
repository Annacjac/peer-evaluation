package edu.tcu.cs.peerevalbackend.admin;

import edu.tcu.cs.peerevalbackend.student.Student;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import edu.tcu.cs.peerevalbackend.student.StudentRepository;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeamRepository;
import edu.tcu.cs.peerevalbackend.student.dto.StudentDto;
import edu.tcu.cs.peerevalbackend.admin.Email.EmailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private EmailService emailService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SeniorDesignTeamRepository teamRepository;

    @InjectMocks
    private AdminService adminService;

    @Test
    public void testAssignStudentsToTeam() throws MessagingException {
        // Arrange
        SeniorDesignTeam team = new SeniorDesignTeam();
        team.setId("Team Alpha");

        Student student1 = new Student();
        student1.setId("1");
        student1.setEmail("student1@example.com");

        Student student2 = new Student();
        student2.setId("2");
        student2.setEmail("student2@example.com");

        List<Student> students = Arrays.asList(student1, student2);
        List<String> studentIds = Arrays.asList(student1.getId(), student2.getId());

        when(teamRepository.findById(anyString())).thenReturn(Optional.of(team));
        when(studentRepository.findAllById(studentIds)).thenReturn(students);

        // Act
        adminService.assignStudentsToTeam(team.getName(), studentIds);

        // Assert
        // Verify that a notification email has been sent to each student
        for (Student student : students) {
            verify(emailService).sendEmailToRecipient(eq(student.getEmail()), anyString(), anyString());
        }

        // Verify that each student has been saved with the appropriate team
        ArgumentCaptor<Student> studentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository, times(students.size())).save(studentCaptor.capture());

        List<Student> savedStudents = studentCaptor.getAllValues();

        for (Student student : savedStudents) {
            assertEquals(team, student.getTeam());
        }
    }
    @Test
    public void testRemoveStudentFromTeam() {
        // Arrange
        String studentId = "1";
        Student student = new Student();
        student.setId(studentId);
        student.setEmail("student@example.com");

        // Properly set the first and last names
        student.setFirstName("John");
        student.setLastName("Doe");

        // Setting a team to the student to simulate that they are part of a team before removal
        SeniorDesignTeam existingTeam = new SeniorDesignTeam();
        existingTeam.setId("team 1"); // Some existing team ID
        student.setTeam(existingTeam);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        // Act
        adminService.removeStudentFromTeam(studentId);

        // Assert
        // Verify that the student's team has been set to null (i.e., removed from the team)
        ArgumentCaptor<Student> studentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentCaptor.capture());
        assertNull(studentCaptor.getValue().getTeam(), "The student's team should be null after removal.");

        // Verify that a notification email has been sent
        try {
            verify(emailService).sendEmailToRecipient(eq(student.getEmail()), anyString(), anyString());
        } catch (MessagingException e) {
            fail("No exception should be thrown when verifying interactions with a mock.");
        }
    }

    @Test
    public void testDeleteSeniorDesignTeam() {
        // Arrange
        String teamId = "Senior Design Team";
        SeniorDesignTeam team = new SeniorDesignTeam();
        team.setId(teamId);

        // Mock repository behavior
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));

        // Act
        adminService.deleteSeniorDesignTeam(teamId);

        // Assert
        verify(teamRepository, times(1)).delete(team);
    }
    /*
    @Test
    public void testUpdateStudentDetails() {
        // Arrange
        Long studentId = 1L;
        Student existingStudent = new Student();
        existingStudent.setId(Math.toIntExact(studentId));
        existingStudent.setFirstName("OldFirstName");
        existingStudent.setLastName("OldLastName");
        existingStudent.setEmail("old@example.com");

        StudentDto updateDto = new StudentDto(1,"new@example.com","NewFirstName",'m', "NewLastName","password", null);


        when(studentRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));

        // Act
        adminService.updateStudentDetails(studentId, updateDto);

        // Assert
        ArgumentCaptor<Student> studentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentCaptor.capture());
        Student savedStudent = studentCaptor.getValue();

        assertEquals("NewFirstName", savedStudent.getFirstName(), "First name should be updated.");
        assertEquals("NewLastName", savedStudent.getLastName(), "Last name should be updated.");
        assertEquals("new@example.com", savedStudent.getEmail(), "Email should be updated.");

    }

     */
}

