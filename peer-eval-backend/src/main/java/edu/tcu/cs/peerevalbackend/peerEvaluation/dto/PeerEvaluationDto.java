package edu.tcu.cs.peerevalbackend.peerEvaluation.dto;

import edu.tcu.cs.peerevalbackend.student.dto.StudentDto;

public record PeerEvaluationDto(Long id,
                                Integer qualityOfWork,
                                String publicComments,
                                String privateComments,
                                String week,
                                StudentDto evaluator,
                                StudentDto evaluatee) {
}
