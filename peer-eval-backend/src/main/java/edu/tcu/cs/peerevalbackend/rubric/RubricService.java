package edu.tcu.cs.peerevalbackend.rubric;


import edu.tcu.cs.peerevalbackend.rubric.converter.RubricDtoConverter;
import edu.tcu.cs.peerevalbackend.rubric.dto.RubricDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RubricService {
    @Autowired
    private RubricRepository rubricRepository;

    public RubricDto getRubricDetails(Integer rubricId) {
        Rubric rubric = rubricRepository.findById(rubricId).orElseThrow(() -> new RuntimeException("Rubric not found"));
        return RubricDtoConverter.convertToRubricDTO(rubric);
    }

    public List<RubricDto> getAllRubrics() {
        List<Rubric> rubrics = rubricRepository.findAll();
        return RubricDtoConverter.convertToRubricDTOList(rubrics);
    }

    public Rubric createRubric(Rubric rubric) {
        return rubricRepository.save(rubric);
    }
}

