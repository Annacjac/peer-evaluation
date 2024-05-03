package edu.tcu.cs.peerevalbackend.peerEvaluation;

import edu.tcu.cs.peerevalbackend.peerEvaluation.dto.PeerEvaluationDto;
import edu.tcu.cs.peerevalbackend.peerEvaluation.dto.PeerEvaluationReportDto;
import edu.tcu.cs.peerevalbackend.peerEvaluation.dto.ReportParametersDto;
import edu.tcu.cs.peerevalbackend.student.Student;
import edu.tcu.cs.peerevalbackend.student.converter.StudentToStudentDtoConverter;
import edu.tcu.cs.peerevalbackend.student.dto.StudentDto;
import edu.tcu.cs.peerevalbackend.system.exception.DataNotFoundException;
import edu.tcu.cs.peerevalbackend.system.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.tcu.cs.peerevalbackend.repository.PeerEvaluationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PeerEvaluationService {

    private static final Logger log = LoggerFactory.getLogger(PeerEvaluationService.class);
    private final PeerEvaluationRepository peerEvaluationRepository;

    @Autowired
    public PeerEvaluationService(PeerEvaluationRepository peerEvaluationRepository) {
        this.peerEvaluationRepository = peerEvaluationRepository;
    }

    @Transactional
    public PeerEvaluation submitEvaluation(PeerEvaluation evaluation) {
        log.info("Submitting peer evaluation for evaluator {} on evaluatee {}", evaluation.getEvaluator().getEmail(), evaluation.getEvaluatee().getEmail());
        PeerEvaluation savedEvaluation = peerEvaluationRepository.save(evaluation);
        log.info("Submitted peer evaluation with ID: {}", savedEvaluation.getId());
        return savedEvaluation;
    }

    @Transactional(readOnly = true)
    public PeerEvaluation findEvaluationById(Long id) {
        return peerEvaluationRepository.findById(id).orElseThrow(() -> new EvaluationNotFoundException("Peer evaluation with ID " + id + " not found."));
    }

    /**
     * Retrieves peer evaluations where the given student is the evaluatee.
     * @param studentId the ID of the student whose report is to be generated
     * @return a list of PeerEvaluation
     */
    @Transactional(readOnly = true)
    public List<PeerEvaluation> findEvaluationsByEvaluateeId(Long studentId) {
        return this.peerEvaluationRepository.findByEvaluateeId(studentId);  // Assume this method is implemented in the repository
    }

    public List<PeerEvaluation> findAllByWeek(String peerEvaluationWeek){
        return peerEvaluationRepository.findAllByWeek(peerEvaluationWeek);
    }

    public List<PeerEvaluation> findAllByEvaluateeId(Long id){
        return peerEvaluationRepository.findAllByEvaluateeId(id);
    }

    public PeerEvaluationReportDto generateReport(ReportParametersDto reportParams) throws ValidationException, DataNotFoundException {
        validateReportParameters(reportParams);
        List<PeerEvaluation> evaluations = findEvaluationsForWeek(reportParams.getActiveWeek());
        if (evaluations.isEmpty()) {
            throw new DataNotFoundException("No data available for the given week.");
        }
        return convertToReportDto(evaluations, reportParams);
    }

    private void validateReportParameters(ReportParametersDto params) throws ValidationException {
        List<String> errors = new ArrayList<>();
        if (params.getActiveWeek() == null || params.getActiveWeek().isEmpty()) {
            errors.add("Active week must be specified.");
        }
        if (params.getColumns() == null || params.getColumns().isEmpty()) {
            errors.add("At least one column must be specified.");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    private List<PeerEvaluation> findEvaluationsForWeek(String week) {
        return peerEvaluationRepository.findByWeek(week);
    }

    private PeerEvaluationReportDto convertToReportDto(List<PeerEvaluation> evaluations, ReportParametersDto params) {
        Double qualityOfWorkAverage = evaluations.stream()
                .mapToInt(PeerEvaluation::getQualityOfWork)
                .average()
                .orElse(0.0); // Calculate the average quality of work

        String publicComments = evaluations.stream()
                .map(PeerEvaluation::getPublicComments)
                .collect(Collectors.joining(" ")); // Aggregate all public comments

        // Assume overall grade is represented by the average quality of work for simplicity
        Integer overallGrade = qualityOfWorkAverage.intValue();

        String week = params.getActiveWeek();

        String studentName = evaluations.isEmpty() ? "No Data" :
                evaluations.get(0).getEvaluatee().getFirstName() + " " + evaluations.get(0).getEvaluatee().getLastName();

        return new PeerEvaluationReportDto(studentName, qualityOfWorkAverage, publicComments, overallGrade, week);
    }
}

class EvaluationNotFoundException extends RuntimeException {
    public EvaluationNotFoundException(String message) {
        super(message);
    }
}