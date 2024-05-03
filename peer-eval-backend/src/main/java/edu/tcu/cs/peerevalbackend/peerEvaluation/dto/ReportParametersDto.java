package edu.tcu.cs.peerevalbackend.peerEvaluation.dto;

import java.util.List;

public class ReportParametersDto {
    private String activeWeek;

    public String getActiveWeek() {
        return activeWeek;
    }

    public void setActiveWeek(String activeWeek) {
        this.activeWeek = activeWeek;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    private List<String> columns;


}
