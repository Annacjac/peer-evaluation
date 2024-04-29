package edu.tcu.cs.peerevalbackend.section;

import edu.tcu.cs.peerevalbackend.section.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Sort;

import java.util.List;


public interface SectionRepository extends JpaRepository<Section, Integer> {
    // Finds sections by name and academic year dynamically
    List<Section> findBySectionNameAndAcademicYear(String sectionName, String academicYear, Sort sort);
}