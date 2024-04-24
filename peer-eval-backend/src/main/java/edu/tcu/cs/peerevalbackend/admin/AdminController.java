package edu.tcu.cs.peerevalbackend.admin;

import edu.tcu.cs.peerevalbackend.admin.dto.AdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import peerevalbackend.admin.service.AdminService;

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
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}