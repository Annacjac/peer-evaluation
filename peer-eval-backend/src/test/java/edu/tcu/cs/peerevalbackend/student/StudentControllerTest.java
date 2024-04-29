package edu.tcu.cs.peerevalbackend.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.peerevalbackend.student.dto.StudentDto;
import edu.tcu.cs.peerevalbackend.system.StatusCode;
import edu.tcu.cs.peerevalbackend.system.exception.AlreadyExistsException;
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
    void testRegisterStudentSuccess() throws Exception {
        //Given
        StudentDto studentDto = new StudentDto("student4@gmail.com",
                "Greg",
                ' ',
                "Universe",
                "password4",
                null);

        String json = this.objectMapper.writeValueAsString(studentDto);

        Student savedStudent = new Student();
        savedStudent.setEmail("student4@gmail.com");
        savedStudent.setFirstName("Greg");
        savedStudent.setLastName("Universe");
        savedStudent.setPassword("password 4");

        given(this.studentService.save(Mockito.any(Student.class))).willReturn(savedStudent);

        //When and then
        this.mockMvc.perform(post(this.baseUrl + "/students").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.email").isNotEmpty())
                .andExpect(jsonPath("$.data.firstName").value(savedStudent.getFirstName()))
                .andExpect(jsonPath("$.data.lastName").value(savedStudent.getLastName()))
                .andExpect(jsonPath("$.data.password").value(savedStudent.getPassword()));
    }

    @Test
    void testRegisterStudentFailed() throws Exception, ValidationException {
        // Given
        StudentDto studentDto = new StudentDto("student3@gmail.com", "Random", ' ', "Name", "password5", null);

        when(studentService.registerStudent(any(StudentDto.class))).thenThrow(new AlreadyExistsException("Account already exists"));

        // When & Then
        mockMvc.perform(post("/students/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDto)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof AlreadyExistsException))
                .andExpect(jsonPath("$.message").value("Account already exists"));
    }
}