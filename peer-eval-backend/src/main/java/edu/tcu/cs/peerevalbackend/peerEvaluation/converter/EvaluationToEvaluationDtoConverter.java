package edu.tcu.cs.peerevalbackend.peerEvaluation.converter;

import edu.tcu.cs.peerevalbackend.peerEvaluation.dto.PeerEvaluationDto;
import edu.tcu.cs.peerevalbackend.peerEvaluation.PeerEvaluation;
import edu.tcu.cs.peerevalbackend.student.converter.StudentDtoToStudentConverter;
import edu.tcu.cs.peerevalbackend.student.converter.StudentToStudentDtoConverter;
import edu.tcu.cs.peerevalbackend.student.dto.StudentDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EvaluationToEvaluationDtoConverter implements Converter<PeerEvaluation, PeerEvaluationDto> {
    private final StudentToStudentDtoConverter studentToStudentDtoConverter;

    public EvaluationToEvaluationDtoConverter(StudentToStudentDtoConverter studentToStudentDtoConverter) {
        this.studentToStudentDtoConverter = studentToStudentDtoConverter;
    }

    @Override
    public PeerEvaluationDto convert(PeerEvaluation source) {
        PeerEvaluationDto peerEvaluationDto = new PeerEvaluationDto(source.getId(),
                source.getQualityOfWork(),
                source.getPublicComments(),
                source.getPrivateComments(),
                source.getWeek(),
                source.getEvaluator() != null
                        ? this.studentToStudentDtoConverter.convert(source.getEvaluator())
                        : null,
                source.getEvaluatee() != null
                        ? this.studentToStudentDtoConverter.convert(source.getEvaluatee())
                        : null);
        return peerEvaluationDto;
    }
}

