package edu.tcu.cs.peerevalbackend.rubric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rubrics")
public class RubricController {
    @Autowired
    private RubricService rubricService;

    @PostMapping
    public ResponseEntity<Rubric> createRubric(@RequestBody Rubric rubric) {
        try {
            Rubric newRubric = rubricService.createRubric(rubric);
            return ResponseEntity.ok(newRubric);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
