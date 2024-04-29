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

        @OneToMany(mappedBy="name", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        private List<Student> students = new ArrayList<>();

        @OneToOne
        private Instructor instructor;

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

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }

        public Instructor getInstructor() {
            return instructor;
        }

        public void setInstructor(Instructor instructor) {
            this.instructor = instructor;
        }

        public Section getSection() {
            return section;
        }

        public void setSection(Section section) {
            this.section = section;
        }

    }
