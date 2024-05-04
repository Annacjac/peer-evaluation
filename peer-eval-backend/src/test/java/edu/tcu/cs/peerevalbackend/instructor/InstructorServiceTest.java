package edu.tcu.cs.peerevalbackend.instructor;

import edu.tcu.cs.peerevalbackend.instructor.dto.InstructorDto;
import edu.tcu.cs.peerevalbackend.instructor.service.InstructorService;
import edu.tcu.cs.peerevalbackend.instructor.service.InstructorServiceImpl;

import edu.tcu.cs.peerevalbackend.peerEvaluation.PeerEvaluation;
import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.section.SectionRepository;
import edu.tcu.cs.peerevalbackend.student.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorServiceTest {

    @Mock
    InstructorRepository instructorRepository;



    @InjectMocks
    InstructorService instructorService;

    List<Instructor> instructors;

    List<Section> sections;

    List<PeerEvaluation> peerEvaluations1;
    List<PeerEvaluation> peerEvaluations2;


    @Mock
    SectionRepository sectionRepository;

    @BeforeEach
    void setUp() {
        Instructor i1 = new Instructor();
        i1.setId(1);
        i1.setFirstName("Wei");
        i1.setLastName("Bingyang");

        Instructor i2 = new Instructor();
        i2.setId(2);
        i2.setFirstName("Ma");
        i2.setLastName("Liran");


        Section sec1 = new Section();
        //sec1.setAdmin(i1);
        ArrayList<String> activeWeeks = new ArrayList<>();
        activeWeeks.add("02-12-2024");
        sec1.setActiveWeeks(activeWeeks);

        Student carlos = new Student();
        carlos.setId("1");
        Student eriife = new Student();
        eriife.setId("2");

        PeerEvaluation carlosToEriife = new PeerEvaluation();
        carlosToEriife.setEvaluator(carlos);
        carlosToEriife.setEvaluatee(eriife);
        carlosToEriife.setQualityOfWork(10);
        carlosToEriife.setPublicComments("Public");
        carlosToEriife.setPrivateComments("Private");

        PeerEvaluation carlosToCarlos = new PeerEvaluation();
        carlosToCarlos.setEvaluator(carlos);
        carlosToCarlos.setEvaluatee(carlos);
        carlosToCarlos.setQualityOfWork(8);
        carlosToCarlos.setPublicComments("Public");
        carlosToCarlos.setPrivateComments("Private");

        PeerEvaluation eriifeToCarlos = new PeerEvaluation();
        eriifeToCarlos.setEvaluator(eriife);
        eriifeToCarlos.setEvaluatee(carlos);
        eriifeToCarlos.setQualityOfWork(10);
        eriifeToCarlos.setPublicComments("Public");
        eriifeToCarlos.setPrivateComments("Private");

        PeerEvaluation eriifeToEriife = new PeerEvaluation();
        eriifeToEriife.setEvaluator(eriife);
        eriifeToEriife.setEvaluatee(eriife);
        eriifeToEriife.setQualityOfWork(8);
        eriifeToEriife.setPublicComments("Public");
        eriifeToEriife.setPrivateComments("Private");

        peerEvaluations1 = new ArrayList<>();
        peerEvaluations1.add(carlosToEriife);
        peerEvaluations1.add(carlosToCarlos);

        peerEvaluations2 = new ArrayList<>();
        peerEvaluations2.add(eriifeToCarlos);
        peerEvaluations2.add(eriifeToEriife);

        this.instructors = new ArrayList<>();
        this.instructors.add(i1);
        this.instructors.add(i2);

        this.sections = new ArrayList<>();
        this.sections.add(sec1);

    }

    /*
    @Test //Add an instructor
    void testSaveSuccess(){
        // Given
        Instructor newInstructor = new Instructor();
        newInstructor.setId(1);
        newInstructor.setFirstName("Wei");
        newInstructor.setLastName("Bingyang");

        given(instructorRepository.save(newInstructor)).willReturn(newInstructor);

        // When
        Instructor savedInstructor = InstructorService.save(newInstructor);

        // Then
        assertThat(savedInstructor.getId()).isEqualTo(1);
        assertThat(savedInstructor.getFirstName()).isEqualTo("Wei");
        assertThat(savedInstructor.getLastName()).isEqualTo("Bingyang");
        verify(instructorRepository, times(1)).save(newInstructor);

    }


    @Test
    void testCreateInstructor() {
        InstructorDto newInstructorDto = new InstructorDto(null, "Alice", "Wonderland", "alice@example.com");
        Instructor newInstructor = new Instructor();
        newInstructor.setId(1);
        newInstructor.setFirstName("Alice");
        newInstructor.setLastName("Wonderland");
        newInstructor.setEmail("alice@example.com");
        newInstructor.setActive(true);

        when(instructorRepository.save(any(Instructor.class))).thenReturn(newInstructor);

        InstructorDto savedInstructorDto = instructorService.createInstructor(newInstructorDto);

        assertNotNull(savedInstructorDto);
        assertEquals("Alice", savedInstructorDto.firstName());
        assertEquals("Wonderland", savedInstructorDto.lastName());
        assertEquals("alice@example.com", savedInstructorDto.email());
    }

     */


}
