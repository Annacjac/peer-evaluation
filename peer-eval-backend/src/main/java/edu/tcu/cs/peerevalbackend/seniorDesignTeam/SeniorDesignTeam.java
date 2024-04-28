package edu.tcu.cs.peerevalbackend.seniorDesignTeam;


import edu.tcu.cs.peerevalbackend.instructor.Instructor;
import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.student.Student;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
    public class SeniorDesignTeam implements Serializable {
        @Id
        private String name;

        @OneToMany(mappedBy="name", cascade={CascadeType.PERSIST, CascadeType.MERGE})
        private List<Student> students = new ArrayList<>();

        @OneToOne
        private Instructor teamInstructor;

        @ManyToOne
        private Section section;

        public SeniorDesignTeam(){
        }
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
        public void removeAllStudents(){
            this.students.stream().forEach(student -> student.setAssignedTeam(null));
            this.students = new ArrayList<>();
        }
        public void removeStudent(Student studentToBeRemoved){
            studentToBeRemoved.setAssignedTeam(null);
            this.students.remove(studentToBeRemoved);
        }
        public Instructor getInstructor(){
            return teamInstructor;
        }
        public void setInstructor(Instructor teamInstructor){
            this.teamInstructor = teamInstructor;
        }
    }

