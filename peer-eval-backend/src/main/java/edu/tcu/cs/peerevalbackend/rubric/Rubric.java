package edu.tcu.cs.peerevalbackend.rubric;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class Rubric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "rubric", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Criterion> criteria;

    public Rubric() {}

    public Rubric(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Rubric(String name, String description, List<Criterion> criteria) {
        this.name = name;
        this.description = description;
        this.criteria = criteria;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Criterion> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criterion> criteria) {
        this.criteria = criteria;
    }

    // Convenient method to add a criterion to the rubric
    public void addCriterion(Criterion criterion) {
        this.criteria.add(criterion);
        criterion.setRubric(this);
    }
}


