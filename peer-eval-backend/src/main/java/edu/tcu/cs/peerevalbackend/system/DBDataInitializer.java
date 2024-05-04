package edu.tcu.cs.peerevalbackend.system;

import edu.tcu.cs.peerevalbackend.admin.Admin;
import edu.tcu.cs.peerevalbackend.admin.AdminRepository;
import edu.tcu.cs.peerevalbackend.instructor.Instructor;
import edu.tcu.cs.peerevalbackend.instructor.InstructorRepository;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeamRepository;
import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.section.SectionRepository;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final SectionRepository sectionRepository;
    private final InstructorRepository instructorRepository;
    private final AdminRepository adminRepository;
    private final SeniorDesignTeamRepository teamRepository;


    public DBDataInitializer(SectionRepository sectionRepository, InstructorRepository instructorRepository, AdminRepository adminRepository, SeniorDesignTeamRepository teamRepository) {
        this.sectionRepository = sectionRepository;
        this.instructorRepository = instructorRepository;
        this.adminRepository = adminRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Section Initialization
        Section sec1 = new Section();
        sec1.setId("Section 2017-2018");
        sec1.setAcademicYear("2017-2018");
        sec1.setFirstDate("08/21/17");
        sec1.setLastDate("05/01/18");

        Section sec2 = new Section();
        sec2.setId("Section 2018-2019");
        sec2.setAcademicYear("2018-2019");
        sec2.setFirstDate("08/21/18");
        sec2.setLastDate("05/01/19");

        Section sec3 = new Section();
        sec3.setId("Section 2019-2020");
        sec3.setAcademicYear("2019-2020");
        sec3.setFirstDate("08/21/19");
        sec3.setLastDate("05/01/20");

        Section sec4 = new Section();
        sec4.setId("Section 2020-2021");
        sec4.setAcademicYear("2020-2021");
        sec4.setFirstDate("08/21/20");
        sec4.setLastDate("05/01/21");

        Section sec5 = new Section();
        sec5.setId("Section 2021-2022");
        sec5.setAcademicYear("2021-2022");
        sec5.setFirstDate("08/21/21");
        sec5.setLastDate("05/01/22");

        Section sec6 = new Section();
        sec6.setId("Section 2022-2023");
        sec6.setAcademicYear("2022-2023");
        sec6.setFirstDate("08/21/22");
        sec6.setLastDate("05/01/23");

        Section sec7 = new Section();
        sec7.setId("Section 2023-2024");
        sec7.setAcademicYear("2023-2024");
        sec7.setFirstDate("08/21/23");
        sec7.setLastDate("05/01/24");

        Section sec8 = new Section();
        sec8.setId("Section 2023-2024-02");
        sec8.setAcademicYear("2023-2024");
        sec8.setFirstDate("08/21/23");
        sec8.setLastDate("05/01/24");

        //Admin Initialization
        Admin adm1 = new Admin();
        adm1.setId(1);
        adm1.setName("Bingyang Wei");

        Admin adm2 = new Admin();
        adm2.setId(2);
        adm2.setName("Mr. Humpty Dumpty");


        //Instructor Initialization
        Instructor instructor = new Instructor();
        instructor.setId(1);
        instructor.setFirstName("Test instructor");
        instructor.setLastName("Test instructor");

        Instructor instructor2 = new Instructor();
        instructor2.setId(2);
        instructor2.setFirstName("Test instructor2");
        instructor2.setLastName("Test instructor2");

        List<Instructor> instructors = Arrays.asList(instructor, instructor2);

        //Teams Initialization
        SeniorDesignTeam team1 = new SeniorDesignTeam();
        team1.setName("Team 1");
        team1.setAcademicYear("2023-24");
        team1.setSection(sec7);
        team1.setAdmin(adm1);

        SeniorDesignTeam team2 = new SeniorDesignTeam();
        team2.setName("Team 2");
        team2.setAcademicYear("2020-21");
        team2.setInstructors(instructors);
        team2.setSection(sec7);
        team2.setAdmin(adm1);

        SeniorDesignTeam team3 = new SeniorDesignTeam();
        team3.setName("Team 3");
        team3.setAcademicYear("2023-24");
        team3.setInstructors(instructors);
        team3.setAdmin(adm1);

        //Populating admin with sections
        adm1.addSection(sec2);
        adm1.addSection(sec3);
        adm1.addSection(sec4);
        adm1.addSection(sec5);
        adm1.addSection(sec6);
        adm1.addSection(sec7);
        adm1.addSection(sec8);

        //Populating admin with teams.
        adm1.addTeam(team1);
        adm2.addTeam(team2);

        //Saving entities in H2 database using repository save.
        //Admin
        adminRepository.save(adm1);
        adminRepository.save(adm2);

        //Section
        sectionRepository.save(sec1);

        //Instructor
        instructorRepository.save(instructor);

        //Team
        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);
    }
}

