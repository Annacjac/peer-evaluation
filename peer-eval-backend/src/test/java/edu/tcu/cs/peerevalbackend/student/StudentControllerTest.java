package edu.tcu.cs.peerevalbackend.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.peerevalbackend.section.Section;
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
import java.util.Arrays;
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
    Section section;

    @Value("/api/v1")
    String baseUrl;

    @BeforeEach
    void setUp(){
        this.section = new Section();
        section.setSectionName("Section1");
        this.students = new ArrayList<>();

        Student s1 = new Student();
        s1.setId(1);
        s1.setEmail("student1@gmail.com");
        s1.setFirstName("John");
        s1.setLastName("Doe");
        s1.setPassword("password1");
        s1.setSectionName("Section1");
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
    void testFindStudentBySectionSuccess() throws Exception{
        //Given
        List<Student> expectedStudents = Arrays.asList(students.get(0));
        given(this.studentService.findBySectionName("Section1")).willReturn(expectedStudents);

        //When and Then
        this.mockMvc.perform(get(this.baseUrl + "/sections/Section1/students").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find Success"))
                .andExpect(jsonPath("$.data.firstName").value("John"))
                .andExpect(jsonPath("$.data.lastName").value("Doe"))
                .andExpect(jsonPath("$.data.email").value("student1@gmail.com"));
    }
    @Test
    void testFindStudentBySectionNotFound() throws Exception{
        //Given
        given(this.studentService.findBySectionName("Section10")).willThrow(new ObjectNotFoundException("section", "Section10"));
        //When and Then
        this.mockMvc.perform(get(this.baseUrl + "/sections/Section10/students").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find Success"))
                .andExpect(jsonPath("$.data.firstName").value("John"))
                .andExpect(jsonPath("$.data.lastName").value("Doe"))
                .andExpect(jsonPath("$.data.email").value("student1@gmail.com"));
    }
}