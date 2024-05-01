package edu.tcu.cs.peerevalbackend.admin;

import edu.tcu.cs.peerevalbackend.section.SectionController;
import edu.tcu.cs.peerevalbackend.section.SectionService;
import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminSectionViewTest {

    @Mock
    private SectionService sectionService;

    @InjectMocks
    private SectionController sectionController;

    @Test
    public void testGetSectionDetailsNotFound() {
        Integer sectionId = 1;
        when(sectionService.getSectionDetails(sectionId)).thenThrow(new ObjectNotFoundException("Section", sectionId));

        ResponseEntity<?> response = sectionController.getSectionDetails(sectionId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Could not find Section with Id 1"));
    }
}

