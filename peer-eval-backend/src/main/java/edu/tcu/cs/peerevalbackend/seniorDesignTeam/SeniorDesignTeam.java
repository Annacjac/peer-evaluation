package edu.tcu.cs.peerevalbackend.seniorDesignTeam;


import edu.tcu.cs.peerevalbackend.instructor.Instructor;
import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.student.Student;
import jakarta.persistence.*;
import javax.print.attribute.IntegerSyntax;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
    public class SeniorDesignTeam implements Serializable {
    @Id
    private String name;

    @OneToMany(mappedBy = "name", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "instructor")
    private List<Instructor> instructors = new ArrayList<>();

    @ManyToOne
    private Section section;

    public SeniorDesignTeam() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Section getSection() {
        return section;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void removeAllStudents() {
        this.students.stream().forEach(student -> student.setTeam(null));
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        student.setTeam(this);
        this.students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
