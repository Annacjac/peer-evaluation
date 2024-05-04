package edu.tcu.cs.peerevalbackend.seniorDesignTeam;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.peerevalbackend.admin.Admin;
import edu.tcu.cs.peerevalbackend.instructor.Instructor;
import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.student.Student;
import edu.tcu.cs.peerevalbackend.system.StatusCode;
import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class SeniorDesignTeamServiceTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    SeniorDesignTeamService seniorDesignTeamService;
    @MockBean
    SeniorDesignTeamRepository seniorDesignTeamRepository;

    List<SeniorDesignTeam> teams;
    @BeforeEach
    void setUp(){
        Student student1 = new Student();
        student1.setId("1");
        student1.setFirstName("John");
        student1.setMidInit('L');
        student1.setLastName("Smith");

        Student student2 = new Student();
        student1.setId("2");
        student1.setFirstName("Ava");
        student1.setMidInit('M');
        student1.setLastName("Brown");

        Student student3 = new Student();
        student1.setId("3");
        student1.setFirstName("Tom");
        student1.setMidInit('S');
        student1.setLastName("Johnson");

        Student student4 = new Student();
        student1.setId("4");
        student1.setFirstName("Josh");
        student1.setMidInit('M');
        student1.setLastName("Peterson");

        Student student5 = new Student();
        student1.setId("5");
        student1.setFirstName("Ellie");
        student1.setMidInit('R');
        student1.setLastName("Thompson");

        Student student6 = new Student();
        student1.setId("6");
        student1.setFirstName("Alex");
        student1.setMidInit('C');
        student1.setLastName("Collins");

        this.teams = new ArrayList<>();


        Admin admin1 = new Admin();
        admin1.setId(1);

        Section section1 = new Section();
        section1.setId("1");
        section1.setAdmin(admin1);

        Instructor instructor1 = new Instructor();
        instructor1.setId(1);
        instructor1.setActive(true);


        SeniorDesignTeam team1 = new SeniorDesignTeam();
        team1.setName("Team-1");
        team1.setSection(section1);
        team1.addStudent(student1);
        team1.addStudent(student2);
        team1.addStudent(student3);

        SeniorDesignTeam team2 = new SeniorDesignTeam();
        team2.setName("Team-2");
        team2.setSection(section1);
        team2.addStudent(student4);
        team2.addStudent(student5);
        team2.addStudent(student6);

        this.teams.add(team1);
        this.teams.add(team2);




    }
    @Test
    void testDeleteTeamSuccess() {
        //Given
        SeniorDesignTeam team1 = new SeniorDesignTeam();
        team1.setName("Team-1");

        given(this.seniorDesignTeamRepository.findById("Team-1")).willReturn(Optional.of(team1));
        doNothing().when(this.seniorDesignTeamRepository).deleteById("Team-1");

        //When
        this.seniorDesignTeamService.deleteTeam("Team-1");

        //Then
        verify(this.seniorDesignTeamRepository, times(1)).deleteById("Team-1");
    }
    @Test
    void testDeleteTeamNotFound(){
        //Given
        given(this.seniorDesignTeamRepository.findById("Team-1")).willReturn(Optional.empty());
        //When
        assertThrows(ObjectNotFoundException.class, () -> {
            this.seniorDesignTeamService.deleteTeam("Team-1");
        });

        //Then
        verify(this.seniorDesignTeamRepository, times(1)).findById("Team-1");
    }
    @Test
    void removeStudentSuccess() throws Exception{
        //Given
        SeniorDesignTeam team1 = new SeniorDesignTeam();
        team1.setId("1");
        Student s = new Student();
        s.setTeam(team1);
        s.setId("1");
        doNothing().when(this.seniorDesignTeamService).removeStudent("1");

        //When and Then
        this.mockMvc.perform(delete("/api/v1/teams/students/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Remove success."))
                .andExpect(jsonPath("$.data").isEmpty());
    }
    @Test
    void removeStudentNonExistent() throws Exception{
        //Given

        //When
    }

}