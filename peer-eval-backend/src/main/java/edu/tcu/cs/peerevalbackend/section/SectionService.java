package edu.tcu.cs.peerevalbackend.section;

import edu.tcu.cs.peerevalbackend.rubric.RubricRepository;
import edu.tcu.cs.peerevalbackend.section.utils.IdWorker;
import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SectionService {


    private final SectionRepository sectionRepository;


    private final RubricRepository rubricRepository;

    private final IdWorker idWorker;

    public SectionService(SectionRepository sectionRepository, RubricRepository rubricRepository, IdWorker idWorker) {
        this.sectionRepository = sectionRepository;
        this.rubricRepository = rubricRepository;
        this.idWorker = idWorker;
    }

    public Section findById(String sectionId) {
        return this.sectionRepository.findById(sectionId)
                .orElseThrow(() -> new ObjectNotFoundException("Section", sectionId));
    }


    public Section save (Section newSection) {
        newSection.setId(idWorker.nextId() + "");
        return this.sectionRepository.save(newSection);
    }

    public List<Section> searchSections(String sectionName, String academicYear) {

        Specification<Section> spec = Specification.where(null);


        if(sectionName != null && !sectionName.isEmpty()) {
            spec = spec.and(SectionSpecs.hasSectionName(sectionName));
        }

        if(academicYear !=null && !academicYear.isEmpty()) {
            spec = spec.and(SectionSpecs.hasAcademicYear(academicYear));
        }

        return sectionRepository.findAll((Sort) spec);


    }
    public Page<Section> findAll(Pageable pageable) {
        return this.sectionRepository.findAll(pageable);
    }

}
