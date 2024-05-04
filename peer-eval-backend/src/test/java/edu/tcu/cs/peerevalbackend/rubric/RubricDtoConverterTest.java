package edu.tcu.cs.peerevalbackend.rubric;

package edu.tcu.cs.peerevalbackend.rubric.converter;

import edu.tcu.cs.peerevalbackend.rubric.Criterion;
import edu.tcu.cs.peerevalbackend.rubric.Rubric;
import edu.tcu.cs.peerevalbackend.rubric.converter.RubricDtoConverter;
import edu.tcu.cs.peerevalbackend.rubric.dto.CriterionDto;
import edu.tcu.cs.peerevalbackend.rubric.dto.RubricDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

public class RubricDtoConverterTest {

    @Test
    public void testConvertToRubricDTO() {
        // Setup - create a rubric with criteria
        Criterion criterion1 = new Criterion("Quality", "Assesses overall quality", 10);
        Criterion criterion2 = new Criterion("Efficiency", "Measures efficiency of the process", 8);
        List<Criterion> criteria = Arrays.asList(criterion1, criterion2);
        Rubric rubric = new Rubric("Course Evaluation", "Evaluates the course", criteria);

        // Act - convert rubric to DTO
        RubricDto rubricDto = RubricDtoConverter.convertToRubricDTO(rubric);

        // Assert - verify that all fields are correctly copied
        assertEquals(rubric.getId(), rubricDto.getId());
        assertEquals(rubric.getName(), rubricDto.getName());
        assertEquals(rubric.getDescription(), rubricDto.getDescription());
        assertEquals(2, rubricDto.getCriteria().size());
        CriterionDto criterionDto1 = rubricDto.getCriteria().get(0);
        CriterionDto criterionDto2 = rubricDto.getCriteria().get(1);

        assertEquals(criterion1.getName(), criterionDto1.getName());
        assertEquals(criterion1.getDescription(), criterionDto1.getDescription());
        assertEquals(criterion1.getMaxScore(), criterionDto1.getMaxScore());

        assertEquals(criterion2.getName(), criterionDto2.getName());
        assertEquals(criterion2.getDescription(), criterionDto2.getDescription());
        assertEquals(criterion2.getMaxScore(), criterionDto2.getMaxScore());
    }
}

