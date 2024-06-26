package edu.tcu.cs.peerevalbackend.seniorDesignTeam.dto;


import edu.tcu.cs.peerevalbackend.instructor.Instructor;
import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.student.Student;

import java.util.List;

public record SeniorDesignTeamDto(String name,
                                  List<Student> students,

                                  List<Instructor> instructors,
                                  Section section){

    private static List<Assignment> assignments;

    public static class Assignment {
        private String instructorId;
        private Long teamId;

        public Assignment(String teamId, String instructorId) {
        }

        public String getInstructorId() {
            return instructorId;
        }

        public void setInstructorId(String instructorId) {
            this.instructorId = instructorId;
        }

        public Long getTeamId() {
            return teamId;
        }

        public void setTeamId(Long teamId) {
            this.teamId = teamId;
        }
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
