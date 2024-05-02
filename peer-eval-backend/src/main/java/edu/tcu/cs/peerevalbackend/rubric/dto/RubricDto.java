package edu.tcu.cs.peerevalbackend.rubric.dto;

import java.util.List;

public class RubricDto {
    private Integer id;
    private String name;
    private String description;
    private List<CriterionDto> criteria;

    // Getters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<CriterionDto> getCriteria() {
        return criteria;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCriteria(List<CriterionDto> criteria) {
        this.criteria = criteria;
    }
}