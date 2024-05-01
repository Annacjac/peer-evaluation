package edu.tcu.cs.peerevalbackend.seniorDesignTeam;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.peerevalbackend.admin.Admin;
import edu.tcu.cs.peerevalbackend.instructor.Instructor;
import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.student.Student;
import edu.tcu.cs.peerevalbackend.system.StatusCode;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

//import java.lang.foreign.SymbolLookup;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)

class SeniorDesignTeamControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    SeniorDesignTeamService seniorDesignTeamService;

    List<SeniorDesignTeam> teams;
    @BeforeEach
    void setUp(){
        Student student1 = new Student();
        student1.setId(1);
        student1.setFirstName("John");
        student1.setMidInit('L');
        student1.setLastName("Smith");

        Student student2 = new Student();
        student1.setId(2);
        student1.setFirstName("Ava");
        student1.setMidInit('M');
        student1.setLastName("Brown");

        Student student3 = new Student();
        student1.setId(3);
        student1.setFirstName("Tom");
        student1.setMidInit('S');
        student1.setLastName("Johnson");

        Student student4 = new Student();
        student1.setId(4);
        student1.setFirstName("Josh");
        student1.setMidInit('M');
        student1.setLastName("Peterson");

        Student student5 = new Student();
        student1.setId(5);
        student1.setFirstName("Ellie");
        student1.setMidInit('R');
        student1.setLastName("Thompson");

        Student student6 = new Student();
        student1.setId(6);
        student1.setFirstName("Alex");
        student1.setMidInit('C');
        student1.setLastName("Collins");

        this.teams = new ArrayList<>();


        Admin admin1 = new Admin();
        admin1.setId(1);

        Section section1 = new Section();
        section1.setId(1);
        section1.setAdmin(admin1);

        Instructor instructor1 = new Instructor();
        instructor1.setId(120805018L);
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
    void findByTeamName() {
    }

    @Test
    void findByStudent() {
    }

    @Test
    void removeStudent() {
    }
    @Test
    void testDeleteTeamSuccess() throws Exception{
        //Given
        doNothing().when(this.seniorDesignTeamService).delete("Team-1");
        //When and Then
        this.mockMvc.perform(delete("/api/v1/teams/Team-1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Delete Success"))
                .andExpect(jsonPath("$.data").isEmpty());
    }


}