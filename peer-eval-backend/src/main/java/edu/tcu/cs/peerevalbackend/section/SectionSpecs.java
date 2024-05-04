package edu.tcu.cs.peerevalbackend.section;

import org.springframework.data.jpa.domain.Specification;

public class SectionSpecs {
    public static Specification<Section> hasSectionName(String givenSectionName){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("sectionName"), givenSectionName);
    }

    public static Specification<Section> hasAcademicYear(String givenAcademicYear){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("academicYear")), "%" + givenAcademicYear.toLowerCase()+ "%");
    }

}
