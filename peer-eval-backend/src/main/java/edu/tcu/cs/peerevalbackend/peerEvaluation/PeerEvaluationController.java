package edu.tcu.cs.peerevalbackend.peerEvaluation;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.tcu.cs.peerevalbackend.peerEvaluation.dto.PeerEvaluationDto;
import edu.tcu.cs.peerevalbackend.peerEvaluation.dto.PeerEvaluationReportDto;
import edu.tcu.cs.peerevalbackend.peerEvaluation.PeerEvaluation;
import edu.tcu.cs.peerevalbackend.peerEvaluation.PeerEvaluationService;
import edu.tcu.cs.peerevalbackend.peerEvaluation.converter.EvaluationDtoToEvaluationConverter;
import edu.tcu.cs.peerevalbackend.peerEvaluation.converter.EvaluationToEvaluationDtoConverter;
import edu.tcu.cs.peerevalbackend.student.Student;
import edu.tcu.cs.peerevalbackend.system.Result;
import edu.tcu.cs.peerevalbackend.system.StatusCode;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/peer-evaluations")
public class PeerEvaluationController {
    private final PeerEvaluationService peerEvaluationService;
    private final EvaluationToEvaluationDtoConverter evaluationToEvaluationDtoConverter;
    private final EvaluationDtoToEvaluationConverter evaluationDtoToEvaluationConverter;

    @Autowired
    public PeerEvaluationController(PeerEvaluationService peerEvaluationService, EvaluationToEvaluationDtoConverter evaluationToEvaluationDtoConverter, EvaluationDtoToEvaluationConverter evaluationDtoToEvaluationConverter) {
        this.peerEvaluationService = peerEvaluationService;
        this.evaluationToEvaluationDtoConverter = evaluationToEvaluationDtoConverter;
        this.evaluationDtoToEvaluationConverter = evaluationDtoToEvaluationConverter;
    }

    @PostMapping
    public ResponseEntity<?> submitPeerEvaluation(@Valid @RequestBody PeerEvaluation evaluation) {
        try {
            PeerEvaluation savedEvaluation = peerEvaluationService.submitEvaluation(evaluation);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEvaluation);
        } catch (Exception e) {
            String errorMessage = "Failed to submit peer evaluation: " + e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEvaluationById(@PathVariable Long id) {
        try {
            PeerEvaluation evaluation = peerEvaluationService.findEvaluationById(id);
            return ResponseEntity.ok(evaluation);
        } catch (EvaluationNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            String errorMessage = "An error occurred while retrieving the peer evaluation: " + e.getMessage();
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @GetMapping("/reports/{studentId}")
    public ResponseEntity<?> getEvaluationsByStudentId(@PathVariable Long studentId) {
        try {
            List<PeerEvaluation> evaluations = peerEvaluationService.findEvaluationsByEvaluateeId(studentId);
            if (evaluations.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(evaluations);
        } catch (Exception e) {
            String errorMessage = "Failed to retrieve peer evaluations: " + e.getMessage();
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @GetMapping("/reports/allbyweek/{peerEvaluationWeek}")
    public Result findAllByWeek(@PathVariable String peerEvaluationWeek) {
        List<PeerEvaluation> foundEvaluations = peerEvaluationService.findAllByWeek(peerEvaluationWeek);
        foundEvaluations.sort(PeerEvaluation::compareTo);
        List<PeerEvaluationDto> evaluationDtos = foundEvaluations.stream()
                .map(evaluationToEvaluationDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All by Week Success", evaluationDtos);
    }

    @GetMapping("/reports/AllByEvaluateeId/{id}")
    public Result findByEvaluateeId(@PathVariable Long id) {
        List<PeerEvaluation> foundEvaluations = peerEvaluationService.findAllByEvaluateeId(id);
        List<PeerEvaluationDto> evaluationDtos = foundEvaluations.stream()
                .map(evaluationToEvaluationDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All by Evaluatee Id Success", evaluationDtos);
    }
}

