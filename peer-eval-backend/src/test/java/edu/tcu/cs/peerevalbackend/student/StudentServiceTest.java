package edu.tcu.cs.peerevalbackend.student;

import edu.tcu.cs.peerevalbackend.student.dto.StudentDto;
import edu.tcu.cs.peerevalbackend.system.exception.AlreadyExistsException;
import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "dev")
class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;

    List<Student> students;

    @BeforeEach
    void setUp(){
        this.students = new ArrayList<>();

        Student s1 = new Student();
        s1.setEmail("student1@gmail.com");
        s1.setFirstName("John");
        s1.setLastName("Doe");
        s1.setPassword("password1");
        this.students.add(s1);

        Student s2 = new Student();
        s2.setEmail("student2@gmail.com");
        s2.setFirstName("Jane");
        s2.setLastName("Dou");
        s2.setPassword("password2");
        this.students.add(s2);

        Student s3 = new Student();
        s3.setEmail("student3@gmail.com");
        s3.setFirstName("Brian");
        s3.setLastName("Smith");
        s3.setPassword("password3");
        this.students.add(s3);
    }

    @AfterEach
    void tearDown(){

    }

    @Test
    void testFindByEmailSuccess(){
        //Given
        Student s = new Student();
        s.setEmail("example@test.com");
        s.setFirstName("Greg");
        s.setLastName("Universe");

        given(this.studentRepository.findById("example@test.com")).willReturn(Optional.of(s));

        //When
        Student returnedStudent = this.studentService.findByEmail("example@test.com");

        //Then
        assertThat(returnedStudent.getEmail()).isEqualTo(s.getEmail());
        assertThat(returnedStudent.getFirstName()).isEqualTo(s.getFirstName());
        assertThat(returnedStudent.getLastName()).isEqualTo(s.getLastName());
        verify(this.studentRepository, times(1)).findById("example@test.com");
    }

    void testFindByEmailNotFound(){
        //Given
        given(this.studentRepository.findById(Mockito.any(String.class))).willReturn(Optional.empty());

        //When
        Throwable thrown = catchThrowable(() -> {
            Student returnedStudent = this.studentService.findByEmail("example@test.com");
        });

        //Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find student with email example@test.com");
        verify(this.studentRepository, times(1)).findById(Mockito.any(String.class));

    }

    @Test
    void testSaveSuccess() {
        //Given
        Student newStudent = new Student();
        newStudent.setEmail("testemail@gmail.com");
        newStudent.setFirstName("Person");
        newStudent.setLastName("Test");
        newStudent.setPassword("password123");

        given(this.studentRepository.save(newStudent)).willReturn(newStudent);

        //When
        Student savedStudent = this.studentService.save(newStudent);

        //Then
        assertThat(savedStudent.getEmail()).isEqualTo("testemail@gmail.com");
        assertThat(savedStudent.getFirstName()).isEqualTo(newStudent.getFirstName());
        assertThat(savedStudent.getLastName()).isEqualTo(newStudent.getLastName());
        assertThat(savedStudent.getPassword()).isEqualTo(newStudent.getPassword());
        verify(this.studentRepository, times(1)).save(newStudent);
    }

    @Test
    void testUpdateSuccess(){
        //Given
        Student oldStudent = new Student();
        oldStudent.setEmail("student1@gmail.com");
        oldStudent.setFirstName("John");
        oldStudent.setLastName("Doe");
        oldStudent.setPassword("password1");

        Student update = new Student();
        update.setFirstName("Greg");
        update.setLastName("Universe");

        given(this.studentRepository.findById("student1@gmail.com")).willReturn(Optional.of(oldStudent));
        given(this.studentRepository.save(oldStudent)).willReturn(oldStudent);

        //when
        Student updatedStudent = this.studentService.update("student1@gmail.com", update);

        //Then
        assertThat(updatedStudent.getEmail()).isEqualTo("student1@gmail.com");
        assertThat(updatedStudent.getFirstName()).isEqualTo(update.getFirstName());
        assertThat(updatedStudent.getLastName()).isEqualTo(update.getLastName());
    }

    @Test
    void testUpdateNotFound() {
        //Given
        Student update = new Student();
        update.setFirstName("Greg");
        update.setLastName("Universe");

        given(this.studentRepository.findById("student5@gmail.com")).willReturn(Optional.empty());

        //When
        assertThrows(ObjectNotFoundException.class, () -> {
            this.studentService.update("student5@gmail.com", update);
        });

        //Then
        verify(this.studentRepository, times(1)).findById("student5@gmail.com");
    }

    /*@Test
    void testRegisterStudentSuccess() {
        // Arrange
        StudentDto studentDto = new StudentDto("test@example.com", "Random", ' ', "Name", "password123", null);

        Student student = new Student();
        student.setEmail(studentDto.email());
        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        student.setPassword(studentDto.password());

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
    }*/
    @Test
    void testFindByLastNameNotFound(){
        //Given
        given(this.studentRepository.findStudentsByLastName(Mockito.any(String.class))).willReturn(Optional.empty());
        //When
        Throwable thrown = catchThrowable(() -> {
            List<Student> students = this.studentService.findByLastName("Smith");
        });

        //Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find student with last name Smith");
                verify(this.studentRepository, times(1)).findStudentsByLastName("Smith");
    }
}
