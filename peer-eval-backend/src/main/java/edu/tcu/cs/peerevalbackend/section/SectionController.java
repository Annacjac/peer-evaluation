package edu.tcu.cs.peerevalbackend.section;

//import edu.tcu.cs.peerevalbackend.service.SectionService;
import edu.tcu.cs.peerevalbackend.section.converter.SectionDtoToSectionConverter;
import edu.tcu.cs.peerevalbackend.section.converter.SectionToSectionDtoConverter;
import edu.tcu.cs.peerevalbackend.section.dto.SectionDto;
import edu.tcu.cs.peerevalbackend.system.Result;
import edu.tcu.cs.peerevalbackend.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
public class SectionController {

    private final SectionService sectionService;

    private final SectionToSectionDtoConverter sectionToSectionDtoConverter;

    private final SectionDtoToSectionConverter sectionDtoToSectionConverter;



    public SectionController(SectionService sectionService, SectionToSectionDtoConverter sectionToSectionDtoConverter, SectionDtoToSectionConverter sectionDtoToSectionConverter) {
        this.sectionService = sectionService;
        this.sectionToSectionDtoConverter = sectionToSectionDtoConverter;
        this.sectionDtoToSectionConverter = sectionDtoToSectionConverter;
    }

    @GetMapping
    public Result findSections(Pageable pageable) throws Exception {
        Page<Section> sectionPage = this.sectionService.findAll(pageable);
        Page<SectionDto> sectionDtoPage = sectionPage
                .map(this.sectionToSectionDtoConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Find All Success", sectionDtoPage);
    }
    @GetMapping("/section_search")
    public Result sectionSearch(
            @RequestParam(value = "sectionName", required = false) String sectionName,
            @RequestParam(value = "academicYear", required = false) String academicYear
    )
    {
        List<Section> foundSections = this.sectionService.searchSections(sectionName, academicYear);
        if(foundSections.size() >1) {


            Comparator<Section> sortByAcademicYear = Comparator.comparing(Section::getAcademicYear,
                    Comparator.reverseOrder());

            Comparator<Section> sortBySectionName = Comparator.comparing(Section::getSectionName);

            Comparator<Section> sortByBoth = sortByAcademicYear.thenComparing(sortBySectionName);
            foundSections.sort(sortByBoth);
        }

        List<SectionDto> sectionDtos = foundSections.stream()
                .map(this.sectionToSectionDtoConverter::convert)
                .toList();


        return new Result(true, StatusCode.SUCCESS, "Search Success", sectionDtos);
    }
    @PostMapping
    public Result addSection(@Valid @RequestBody SectionDto sectionDto){
        Section newSection = this.sectionDtoToSectionConverter.convert(sectionDto);
        Section savedSection = this.sectionService.save(newSection);
        SectionDto savedSectionDto = this.sectionToSectionDtoConverter.convert(savedSection);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedSectionDto) ;
    }

}
