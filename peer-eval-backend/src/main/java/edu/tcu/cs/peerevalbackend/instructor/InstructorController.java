package edu.tcu.cs.peerevalbackend.instructor;

import edu.tcu.cs.peerevalbackend.instructor.Instructor;
import edu.tcu.cs.peerevalbackend.instructor.dto.InstructorDto;
import edu.tcu.cs.peerevalbackend.instructor.dto.InstructorSearchDto;
import edu.tcu.cs.peerevalbackend.instructor.service.InstructorService;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.dto.SeniorDesignTeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    @Autowired
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/{id}")
    public Instructor findById(@PathVariable Long id) {
        return instructorService.findById(id);
    }

    @PostMapping("/")
    public void save(@RequestBody Instructor instructor) {
        instructorService.save(instructor);
    }

    @PostMapping("/assign-to-teams")
    public ResponseEntity<?> assignInstructorsToTeams(@RequestBody SeniorDesignTeamDto seniorDesignTeamDto) {
        try {
            instructorService.assignInstructorsToTeams(seniorDesignTeamDto);
            return ResponseEntity.ok("Instructors assigned to teams successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to assign instructors: " + e.getMessage());
        }
    }

    @DeleteMapping("/remove-from-team/{teamId}/{instructorId}")
    public ResponseEntity<?> removeInstructorFromTeam(@PathVariable Long teamId, @PathVariable Long instructorId) {
        try {
            instructorService.removeInstructorFromTeam(teamId, instructorId);
            return ResponseEntity.ok("Instructor removed from team successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to remove instructor: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Instructor>> searchInstructors(@RequestBody InstructorSearchDto searchCriteria) {
        try {
            List<Instructor> results = instructorService.findInstructors(searchCriteria);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorDto> getInstructorById(@PathVariable String id) {
        try {
            InstructorDto instructor = instructorService.getInstructorById(id);
            return ResponseEntity.ok(instructor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivateInstructor(@PathVariable String id) {
        try {
            instructorService.deactivateInstructor(id);
            return ResponseEntity.ok("Instructor deactivated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to deactivate instructor: " + e.getMessage());
        }
    }

    @PutMapping("/reactivate/{id}")
    public ResponseEntity<String> reactivateInstructor(@PathVariable String id) {
        try {
            instructorService.reactivateInstructor(id);
            return ResponseEntity.ok("Instructor reactivated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to reactivate instructor: " + e.getMessage());
        }
    }
    // Additional endpoints as needed
}
