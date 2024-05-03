package edu.tcu.cs.peerevalbackend.section;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {

    Page<Section> findByCriteria(@Param("sectionName") String sectionName, @Param("academicYear") String academicYear, Pageable pageable);

    boolean existsBySectionName(String sectionName);
}