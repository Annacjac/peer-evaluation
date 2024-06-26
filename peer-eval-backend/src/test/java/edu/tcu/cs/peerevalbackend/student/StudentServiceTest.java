package edu.tcu.cs.peerevalbackend.student;

import edu.tcu.cs.peerevalbackend.peerEvaluation.PeerEvaluation;
import edu.tcu.cs.peerevalbackend.peerEvaluation.PeerEvaluationService;
import edu.tcu.cs.peerevalbackend.peerEvaluation.dto.PeerEvaluationDto;
import edu.tcu.cs.peerevalbackend.peerEvaluation.dto.PeerEvaluationReportDto;
import edu.tcu.cs.peerevalbackend.repository.PeerEvaluationRepository;
import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import edu.tcu.cs.peerevalbackend.student.converter.StudentDtoToStudentConverter;
import edu.tcu.cs.peerevalbackend.student.converter.StudentToStudentDtoConverter;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
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

    @Mock
    private StudentDtoToStudentConverter dtoToStudentConverter;

    @Mock
    private StudentToStudentDtoConverter studentToDtoConverter;

    @Mock
    private PeerEvaluationRepository peerEvaluationRepository;

    @Mock
    private PeerEvaluationService peerEvaluationService;

    @BeforeEach
    void setUp() {
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
    void tearDown() {

    }

    @Test
    void testFindByEmailSuccess() {
        // Given
        Student s = new Student();
        s.setEmail("example@test.com");
        s.setFirstName("Greg");
        s.setLastName("Universe");

        given(this.studentRepository.findByEmail("example@test.com")).willReturn(Optional.of(s));

        // When
        Student returnedStudent = this.studentService.findByEmail("example@test.com");

        // Then
        assertThat(returnedStudent.getEmail()).isEqualTo(s.getEmail());
        assertThat(returnedStudent.getFirstName()).isEqualTo(s.getFirstName());
        assertThat(returnedStudent.getLastName()).isEqualTo(s.getLastName());
        verify(this.studentRepository, times(1)).findByEmail("example@test.com");
    }

    @Test
    void testFindByEmailNotFound() {
        // Given
        given(this.studentRepository.findByEmail("example@test.com")).willReturn(Optional.empty());

        // When
        Throwable thrown = catchThrowable(() -> {
            Student returnedStudent = this.studentService.findByEmail("example@test.com");
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find student with Id example@test.com :(");
        verify(this.studentRepository, times(1)).findByEmail("example@test.com");

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
    void testUpdateSuccess() {
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

    @Test
    void testRegisterStudentSuccess() {
        StudentDto studentDto = new StudentDto("4", "student4@gmail.com", "Greg", ' ', "Universe", "password4", null);
        Student student = new Student();
        student.setEmail("student4@gmail.com");
        student.setFirstName("Greg");
        student.setLastName("Universe");
        student.setPassword("password4");

        when(dtoToStudentConverter.convert(studentDto)).thenReturn(student);
        when(studentRepository.findByEmail("student4@gmail.com")).thenReturn(Optional.empty());
        when(studentRepository.save(student)).thenReturn(student);
        when(studentToDtoConverter.convert(student)).thenReturn(studentDto);

        StudentDto savedDto = studentService.registerStudent(studentDto);

        verify(studentRepository).save(student);
        assertNotNull(savedDto);
        assertEquals("Greg", savedDto.firstName());
    }

    @Test
    void testRegisterStudentEmailAlreadyExists() {
        StudentDto studentDto = new StudentDto("5", "student1@gmail.com", "John", ' ', "Doe", "password1", null);

        when(studentRepository.findByEmail("student1@gmail.com")).thenReturn(Optional.of(new Student()));

        assertThrows(IllegalStateException.class, () -> {
            studentService.registerStudent(studentDto);
        });
    }

    @Test
    void testSubmitPeerEvaluation() {
        // Prepare the DTO and the actual conversion to Entity
        PeerEvaluationDto dto = new PeerEvaluationDto(1L, 5, "Good work", "For internal use", "2024-W02",
                new StudentDto("1", "evaluator@email.com", "John", 'D', "Doe", "password", null),
                new StudentDto("2", "evaluatee@email.com", "Jane", 'M', "Doe", "password2", null));

        PeerEvaluation peerEvaluation = studentService.convertToEntity(dto);

        // Mock the repository save response
        when(peerEvaluationRepository.save(any(PeerEvaluation.class))).thenReturn(peerEvaluation);

        // Call the service method
        PeerEvaluationReportDto reportDto = studentService.submitPeerEvaluation(dto);

        // Assertions
        assertNotNull(reportDto);
        assertEquals("Good work", reportDto.getPublicComments());
        assertEquals("2024-W02", reportDto.getWeek());
    }


    @Test
    void testGetPeerEvaluationReport() {
        String email = "student1@gmail.com";
        String week = "2024-W02";
        Student student = new Student();
        student.setEmail(email);
        student.setFirstName("John");
        student.setLastName("Doe");

        List<PeerEvaluation> evaluations = new ArrayList<>();
        PeerEvaluation pe = new PeerEvaluation();
        pe.setId(1L);
        pe.setEvaluator(student);
        pe.setEvaluatee(student);
        pe.setQualityOfWork(5);
        pe.setPublicComments("Excellent work");
        pe.setPrivateComments("N/A");
        pe.setWeek(week);
        evaluations.add(pe);

        when(studentRepository.findByEmail(email)).thenReturn(Optional.of(student));
        when(peerEvaluationRepository.findByWeekAndEvaluatee(week, student)).thenReturn(evaluations);

        PeerEvaluationReportDto result = studentService.getPeerEvaluationReport(email, week);

        assertNotNull(result);
        assertEquals("John Doe", result.getStudentName());
        assertEquals(5.0, result.getQualityOfWorkAverage());
        assertEquals("Excellent work", result.getPublicComments());
    }
  
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
                .hasMessage("Could not find student with Id Smith :(");
                verify(this.studentRepository, times(1)).findStudentsByLastName("Smith");
    }
    @Test
    void testFindByLastNameSuccess(){
        //Given
        Student s = new Student();
        List<Student> expectedStudents = new ArrayList<>();
        s.setFirstName("John");
        s.setLastName("Doe");
        expectedStudents.add(s);

        given(this.studentRepository.findStudentsByLastName("Doe")).willReturn(Optional.of(expectedStudents));
        //When
         List<Student> actualStudents = studentService.findByLastName("Doe");
        //Then
        assertThat(actualStudents.equals(expectedStudents));
        verify(this.studentRepository, times(1)).findStudentsByLastName("Doe");
    }
    @Test
    void testFindByFirstNameSuccess(){
        //Given
        Student s = new Student();
        List<Student> expectedStudents = new ArrayList<>();
        s.setFirstName("John");
        s.setLastName("Doe");
        expectedStudents.add(s);

        given(this.studentRepository.findStudentByFirstName("John")).willReturn(Optional.of(expectedStudents));
        //When
        List<Student> actualStudents = studentService.findByFirstName("John");
        //Then
        assertThat(actualStudents.equals(expectedStudents));
        verify(this.studentRepository, times(1)).findStudentByFirstName("John");
    }
    @Test
    void testFindByFirstNameNotFound(){
        //Given
        given(this.studentRepository.findStudentByFirstName("John")).willReturn(Optional.empty());

        //When
        Throwable thrown = catchThrowable(() -> {
            List<Student> students = this.studentService.findByFirstName("John");
        });

        //Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find student with Id John :(");
        verify(this.studentRepository, times(1)).findStudentByFirstName("John");
    }
    @Test
    void testFindBySectionNameSuccess(){
        //Given
        Student s = new Student();
        Section section1 = new Section();
        section1.setSectionName("Section-1");
        List<Student> expectedStudents = new ArrayList<>();
        s.setFirstName("John");
        s.setLastName("Doe");
        s.setSectionName("Section-1");
        expectedStudents.add(s);


        given(this.studentRepository.findBySectionName("Section-1")).willReturn(Optional.of(expectedStudents));
        //When
        List<Student> actualStudents = studentService.findBySectionName("Section-1");
        //Then
        assertThat(actualStudents.equals(expectedStudents));
        verify(this.studentRepository, times(1)).findBySectionName("Section-1");
    }
    @Test
    void testFindBySectionNameNotFound(){
        //Given
        given(this.studentRepository.findBySectionName("Section-1")).willReturn(Optional.empty());

        //When
        Throwable thrown = catchThrowable(() -> {
            List<Student> students = this.studentService.findBySectionName("Seciton-1");
        });

        //Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find team with Id Team-1 :(");
        verify(this.studentRepository, times(1)).findBySectionName("Team-1");
    }
    @Test
    void testFindByTeamNameSuccess(){
        //Given
        Student s = new Student();
        SeniorDesignTeam s1 = new SeniorDesignTeam();
        s1.setName("Team-1");
        List<Student> expectedStudents = new ArrayList<>();
        s.setFirstName("John");
        s.setLastName("Doe");
        s.setTeam(s1);
        expectedStudents.add(s);


        given(this.studentRepository.findByTeamName("Team-1")).willReturn(Optional.of(expectedStudents));
        //When
        List<Student> actualStudents = studentService.findByTeamName("Team-1");
        //Then
        assertThat(actualStudents.equals(expectedStudents));
        verify(this.studentRepository, times(1)).findByTeamName("Team-1");
    }
    @Test
    void testFindByTeamNameNotFound(){
        //Given
        given(this.studentRepository.findByTeamName("Team-1")).willReturn(Optional.empty());

        //When
        Throwable thrown = catchThrowable(() -> {
            List<Student> students = this.studentService.findByTeamName("Team-1");
        });

        //Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find team with Id Team-1 :(");
        verify(this.studentRepository, times(1)).findByTeamName("Team-1");
    }
    @Test
    void testFindByAcademicYearSuccess(){
        //Given
        Student s = new Student();
        SeniorDesignTeam s1 = new SeniorDesignTeam();
        s1.setName("Team-1");
        List<Student> expectedStudents = new ArrayList<>();
        s.setFirstName("John");
        s.setLastName("Doe");
        s.setTeam(s1);
        s.setAcademicYear("Senior");
        expectedStudents.add(s);


        given(this.studentRepository.findByAcademicYear("Senior")).willReturn(Optional.of(expectedStudents));
        //When
        List<Student> actualStudents = studentService.findByAcademicYear("Senior");
        //Then
        assertThat(actualStudents.equals(expectedStudents));
        verify(this.studentRepository, times(1)).findByAcademicYear("Senior");
    }
    @Test
    void testFindByAcademicYearNotFound(){
        //Given
        given(this.studentRepository.findByAcademicYear("Senior")).willReturn(Optional.empty());

        //When
        Throwable thrown = catchThrowable(() -> {
            List<Student> students = this.studentService.findByAcademicYear("Senior");
        });

        //Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find academic year with Id Senior :(");
        verify(this.studentRepository, times(1)).findByAcademicYear("Senior");
    }
}
