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

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

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


}