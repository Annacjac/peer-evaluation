package edu.tcu.cs.peerevalbackend.seniorDesignTeam;

import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

class TeamServiceTest {

    @InjectMocks
    private SeniorDesignTeamService service;

    @Mock
    private SeniorDesignTeamRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByIdSuccess() {
        SeniorDesignTeam team = new SeniorDesignTeam();
        team.setName("Team A");
        when(repository.findById("Team A")).thenReturn(Optional.of(team));

        SeniorDesignTeam found = service.findById("Team A");
        assertNotNull(found);
        assertEquals("Team A", found.getName());
    }

    @Test
    void testFindByIdNotFound() {
        when(repository.findById("Nonexistent")).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> {
            service.findById("Nonexistent");
        });
    }

    @Test
    void testSaveTeam() {
        SeniorDesignTeam team = new SeniorDesignTeam();
        team.setName("New Team");
        when(repository.save(any(SeniorDesignTeam.class))).thenReturn(team);

        SeniorDesignTeam saved = service.save(team);
        assertNotNull(saved);
        assertEquals("New Team", saved.getName());
    }

    @Test
    void testUpdateTeamSuccess() {
        SeniorDesignTeam existingTeam = new SeniorDesignTeam();
        existingTeam.setName("Existing Team");
        SeniorDesignTeam updatedTeam = new SeniorDesignTeam();
        updatedTeam.setName("Updated Name");

        when(repository.findById("Existing Team")).thenReturn(Optional.of(existingTeam));
        when(repository.existsByName("Updated Name")).thenReturn(false);
        when(repository.save(any(SeniorDesignTeam.class))).thenReturn(updatedTeam);

        SeniorDesignTeam updated = service.update("Existing Team", updatedTeam);
        assertNotNull(updated);
        assertEquals("Updated Name", updated.getName());
    }

//    @Test
//    void testUpdateTeamNameConflict() {
//        SeniorDesignTeam existingTeam = new SeniorDesignTeam();
//        existingTeam.setName("Existing Team");
//        SeniorDesignTeam updatedTeam = new SeniorDesignTeam();
//        updatedTeam.setName("Existing Team");
//
//        when(repository.findById("Existing Team")).thenReturn(Optional.of(existingTeam));
//        when(repository.existsByName("Existing Team")).thenReturn(true);
//
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            service.update("Existing Team", updatedTeam);
//        });
//
//        assertEquals("Another team with the provided name already exists.", exception.getMessage());
//    }
}
