package edu.tcu.cs.peerevalbackend.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.peerevalbackend.peerEvaluation.dto.PeerEvaluationDto;
import edu.tcu.cs.peerevalbackend.peerEvaluation.dto.PeerEvaluationReportDto;
import edu.tcu.cs.peerevalbackend.student.dto.StudentDto;
import edu.tcu.cs.peerevalbackend.system.StatusCode;
import edu.tcu.cs.peerevalbackend.system.exception.AlreadyExistsException;
import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import edu.tcu.cs.peerevalbackend.system.exception.ValidationException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Autowired
    ObjectMapper objectMapper;

    List<Student> students;

    @Value("/api/v1")
    String baseUrl;

    @BeforeEach
    void setUp(){
        this.students = new ArrayList<>();

        Student s1 = new Student();
        s1.setId(1);
        s1.setEmail("student1@gmail.com");
        s1.setFirstName("John");
        s1.setLastName("Doe");
        s1.setPassword("password1");
        this.students.add(s1);

        Student s2 = new Student();
        s2.setId(2);
        s2.setEmail("student2@gmail.com");
        s2.setFirstName("Jane");
        s2.setLastName("Dou");
        s2.setPassword("password2");
        this.students.add(s2);

        Student s3 = new Student();
        s3.setId(3);
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
    void testRegisterStudentSuccess() throws Exception {
        //Given
        Student student = new Student();
        student.setId(4);
        student.setEmail("student4@gmail.com");
        student.setFirstName("Greg");
        student.setLastName("Universe");
        student.setPassword("password 4");

        String json = this.objectMapper.writeValueAsString(student);

        student.setId(4);

        given(this.studentService.save(Mockito.any(Student.class))).willReturn(student);

        //When and then
        this.mockMvc.perform(post(this.baseUrl + "/students").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.email").value("student4@gmail.com"))
                .andExpect(jsonPath("$.data.firstName").value(student.getFirstName()))
                .andExpect(jsonPath("$.data.lastName").value(student.getLastName()))
                .andExpect(jsonPath("$.data.password").value(student.getPassword()));
    }

    @Test
    void testRegisterStudentEmailAlreadyExists() throws Exception {
        // Given
        StudentDto studentDto = new StudentDto(4, "student1@gmail.com", "John", 'D', "Doe", "password1", null);
        String json = this.objectMapper.writeValueAsString(studentDto);

        // Mock the behavior to simulate email already exists scenario
        given(this.studentService.save(any(Student.class))).willThrow(new AlreadyExistsException("Email already exists"));

        // When and then
        this.mockMvc.perform(post(this.baseUrl + "/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.flag").value(false))
                        .andExpect(jsonPath("$.message").value("Email already exists"));
    }

    @Test
    void testFindStudentByEmailSuccess() throws Exception {
        //Given
        given(this.studentService.findByEmail("student1@gmail.com")).willReturn(this.students.get(0));

        //When and then
        this.mockMvc.perform(get(this.baseUrl + "/students/student1@gmail.com").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find Success"))
                .andExpect(jsonPath("$.data.email").value("student1@gmail.com"))
                .andExpect(jsonPath("$.data.firstName").value("John"))
                .andExpect(jsonPath("$.data.lastName").value("Doe"));
    }

    @Test
    void testFindStudentByEmailNotFound() throws Exception {
        //Given
        given(this.studentService.findByEmail("test@example.com")).willThrow(new ObjectNotFoundException("student", "test@example.com"));

        //When and then
        this.mockMvc.perform(get(this.baseUrl + "/students/5").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find student"))
                .andExpect(jsonPath("$.data").isEmpty());
    }
    @Test
    void testFindStudentByFirstNameSuccess() throws Exception{
        //Given
        given(this.studentService.findByFirstName("John")).willReturn(this.students);

        //When and Then
        this.mockMvc.perform(get(this.baseUrl + "/students/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find Success"))
                .andExpect(jsonPath("$.data.firstName").value("John"))
                .andExpect(jsonPath("$.data.lastName").value("Doe"))
                .andExpect(jsonPath("$.data.email").value("student1@gmail.com"));
    }
    @Test
    void testFindStudentByLastNameSuccess() throws Exception{
        //Given
        given(this.studentService.findByLastName("Doe")).willReturn(this.students);

        //When and Then
        this.mockMvc.perform(get(this.baseUrl + "/students/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find Success"))
                .andExpect(jsonPath("$.data.firstName").value("John"))
                .andExpect(jsonPath("$.data.lastName").value("Doe"))
                .andExpect(jsonPath("$.data.email").value("student1@gmail.com"));
    }

    @Test
    void testSubmitPeerEvaluation() throws Exception {
        PeerEvaluationDto dto = new PeerEvaluationDto(1L, 5, "Good work", "For internal use", "2024-W02", new StudentDto(1, "evaluator@email.com", "John", 'D', "Doe", "password", null), new StudentDto(2, "evaluatee@email.com", "Jane", 'M', "Doe", "password2", null));
        String jsonContent = "{...}";  // JSON representation of dto

        when(studentService.submitPeerEvaluation(any(PeerEvaluationDto.class))).thenReturn(new PeerEvaluationReportDto("Student Name", 4.5, "Good work", 85, "2024-W02"));

        mockMvc.perform(post("/students/peer-evaluations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentName").value("Student Name"))
                .andExpect(jsonPath("$.overallGrade").value(85));
    }

    @Test
    void testGetPeerEvaluationReport() throws Exception {
        String email = "student1@gmail.com";
        String week = "2024-W02";
        PeerEvaluationReportDto reportDto = new PeerEvaluationReportDto("John Doe", 5.0, "Excellent work", 85, week);

        when(studentService.getPeerEvaluationReport(email, week)).thenReturn(reportDto);

        mockMvc.perform(get("/peer-evaluations/report")
                .param("week", week)
                .with(user(email).password("password").roles("STUDENT")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentName").value("John Doe"))
                .andExpect(jsonPath("$.overallGrade").value(85));
    }
}