package edu.tcu.cs.peerevalbackend.peerEvaluation.converter;

import edu.tcu.cs.peerevalbackend.peerEvaluation.dto.PeerEvaluationDto;
import edu.tcu.cs.peerevalbackend.peerEvaluation.PeerEvaluation;
import edu.tcu.cs.peerevalbackend.student.converter.StudentDtoToStudentConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EvaluationDtoToEvaluationConverter implements Converter<PeerEvaluationDto, PeerEvaluation> {

    StudentDtoToStudentConverter studentDtoToStudentConverter;
    @Override
    public PeerEvaluation convert(PeerEvaluationDto source) {
        PeerEvaluation peerEvaluation = new PeerEvaluation();
        peerEvaluation.setId(source.id());
        peerEvaluation.setWeek(source.week());
//        peerEvaluation.setEvaluator(source.evaluator());
//        peerEvaluation.setEvaluatee(source.evaluatee());
        peerEvaluation.setQualityOfWork(source.qualityOfWork());
        peerEvaluation.setPublicComments(source.publicComments());
        peerEvaluation.setPrivateComments(source.privateComments());

        return peerEvaluation;
    }
}
