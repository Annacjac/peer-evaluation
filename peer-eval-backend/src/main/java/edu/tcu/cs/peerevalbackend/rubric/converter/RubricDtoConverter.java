package edu.tcu.cs.peerevalbackend.rubric.converter;
import edu.tcu.cs.peerevalbackend.rubric.Criterion;
import edu.tcu.cs.peerevalbackend.rubric.Rubric;
import edu.tcu.cs.peerevalbackend.rubric.dto.CriterionDto;
import edu.tcu.cs.peerevalbackend.rubric.dto.RubricDto;

import java.util.List;
import java.util.stream.Collectors;

public class RubricDtoConverter {

    // Convert a single Rubric entity to RubricDTO
    public static RubricDto convertToRubricDTO(Rubric rubric) {
        RubricDto dto = new RubricDto();
        dto.setId(rubric.getId());
        dto.setName(rubric.getName());
        dto.setDescription(rubric.getDescription());
        if (rubric.getCriteria() != null) {
            dto.setCriteria(rubric.getCriteria().stream()
                    .map(RubricDtoConverter::convertToCriterionDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    // Convert a single Criterion entity to CriterionDTO
    public static CriterionDto convertToCriterionDTO(Criterion criterion) {
        CriterionDto dto = new CriterionDto();
        dto.setId(criterion.getId());
        dto.setName(criterion.getName());
        dto.setDescription(criterion.getDescription());
        dto.setMaxScore(criterion.getMaxScore());
        return dto;
    }

    // Optionally, if needed, add methods to convert from DTOs back to entities
    public static Rubric convertToRubricEntity(RubricDto dto) {
        Rubric rubric = new Rubric();
        rubric.setId(dto.getId());
        rubric.setName(dto.getName());
        rubric.setDescription(dto.getDescription());
        // Handle criteria similarly if necessary
        return rubric;
    }

    // Convert list of Rubric entities to list of RubricDTOs
    public static List<RubricDto> convertToRubricDTOList(List<Rubric> rubrics) {
        return rubrics.stream()
                .map(RubricDtoConverter::convertToRubricDTO)
                .collect(Collectors.toList());
    }
}
