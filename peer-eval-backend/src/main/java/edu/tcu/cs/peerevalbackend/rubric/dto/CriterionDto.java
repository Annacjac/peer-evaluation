package edu.tcu.cs.peerevalbackend.rubric.dto;

public class CriterionDto {
    private Integer id;
    private String name;
    private String description;
    private int maxScore;

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

    public int getMaxScore() {
        return maxScore;
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

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
}