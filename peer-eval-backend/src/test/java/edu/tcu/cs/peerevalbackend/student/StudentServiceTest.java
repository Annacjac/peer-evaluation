package edu.tcu.cs.peerevalbackend.student;

import edu.tcu.cs.peerevalbackend.student.dto.StudentDto;
import edu.tcu.cs.peerevalbackend.system.exception.AlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;

    @Test
    void testRegisterStudentSuccess() {
        // Arrange
        StudentDto studentDto = new StudentDto();
        studentDto.setEmail("test@example.com");
        studentDto.setFirstName("John");
        studentDto.setLastName("Doe");
        studentDto.setPassword("password123");

        Student student = new Student();
        student.setEmail(studentDto.getEmail());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setPassword(studentDto.getPassword());

        when(studentRepository.findByEmail(anyString())).thenReturn(false);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // Act
        StudentDto savedStudentDto = studentService.registerStudent(studentDto);

        // Assert
        assertEquals(studentDto.getEmail(), savedStudentDto.getEmail());
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void testRegisterStudentAccountExists() {
        // Arrange
        StudentDto studentDto = new StudentDto();
        studentDto.setEmail("test@example.com");

        when(studentRepository.findByEmail(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(AlreadyExistsException.class, () -> studentService.registerStudent(studentDto));
    }
}
