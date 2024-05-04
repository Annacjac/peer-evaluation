package edu.tcu.cs.peerevalbackend.admin;

import edu.tcu.cs.peerevalbackend.admin.dto.AdminDto;
import edu.tcu.cs.peerevalbackend.admin.dto.SearchCriteriaDto;
import edu.tcu.cs.peerevalbackend.section.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;


    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/invite-instructors")
    public ResponseEntity<?> inviteInstructors(@RequestBody AdminDto adminDto) {
        try {
            adminService.sendInvitations(adminDto);
            return ResponseEntity.ok("Invitations sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error sending invitations: " + e.getMessage());
        }
    }
    @GetMapping("/find-sections")
    public ResponseEntity<?> findSections(SearchCriteriaDto criteria, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Section> sectionsPage = adminService.findSections(criteria, pageable);
            if (sectionsPage.isEmpty()) {
                return ResponseEntity.ok("No matching sections found.");
            }
            return ResponseEntity.ok(sectionsPage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error in searching sections: " + e.getMessage());
        }
    }
    // Endpoint to assign students to a team
    @PostMapping("/assignStudentsToTeam")
    public ResponseEntity<?> assignStudentsToTeam(@RequestParam String teamId, @RequestBody List<String> studentIds) {
        try {
            adminService.assignStudentsToTeam(teamId, studentIds);
            return ResponseEntity.ok().body("Students have been successfully assigned to the team.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Endpoint to remove a student from a team
    @DeleteMapping("/removeStudentFromTeam/{studentId}")
    public ResponseEntity<?> removeStudentFromTeam(@PathVariable String studentId) {
        try {
            adminService.removeStudentFromTeam(studentId);
            return ResponseEntity.ok("Student removed from the team successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }
    // Endpoint to delete a senior design team
    @DeleteMapping("/deleteSeniorDesignTeam/{teamId}")
    public ResponseEntity<?> deleteSeniorDesignTeam(@PathVariable String teamId) {
        try {
            adminService.deleteSeniorDesignTeam(teamId);
            return ResponseEntity.ok("Senior design team deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }




}