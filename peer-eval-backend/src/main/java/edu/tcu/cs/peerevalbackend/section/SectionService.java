package edu.tcu.cs.peerevalbackend.section;

import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.section.SectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionRepository repository;

    public List<Section> findSections(String sectionName, String academicYear) {
        Sort sort = Sort.by(Sort.Order.desc("academicYear"), Sort.Order.asc("sectionName"));
        return repository.findBySectionNameAndAcademicYear(sectionName, academicYear, sort);
    }
}
