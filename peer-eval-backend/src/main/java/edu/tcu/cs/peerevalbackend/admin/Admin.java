package edu.tcu.cs.peerevalbackend.admin;

import edu.tcu.cs.peerevalbackend.section.Section;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Admin implements Serializable {

    @Id
    private Integer id;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "admin")
    private List<Section> sections;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    // A method to help easily add sections to an admin
    public void addSection(Section section) {
        this.sections.add(section);
        section.setAdmin(this);
    }

}