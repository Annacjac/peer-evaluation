package edu.tcu.cs.peerevalbackend.seniorDesignTeam;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.peerevalbackend.student.Student;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class SeniorDesignTeamControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    SeniorDesignTeamService seniorDesignTeamService;

    List<Student> students;
    List<SeniorDesignTeam> seniorDesignTeams;

    @BeforeEach
    void setUp() throws Exception{
        SeniorDesignTeam sdt1 = new SeniorDesignTeam();
        sdt1.setName("Team1");
        sdt1.set
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
    void removeSeniorDesignTeam(){

    }
}