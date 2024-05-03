package edu.tcu.cs.peerevalbackend.seniorDesignTeam;


import edu.tcu.cs.peerevalbackend.admin.Admin;
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
    @ManyToOne
    private Admin admin;
    private String academicYear;
    @ManyToOne
    private Instructor instructor;

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

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
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
    public void removeStudent(Student studentToBeAssigned){
        studentToBeAssigned.setTeam(null);
        this.students.remove(studentToBeAssigned);
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

    public void setAdmin(Admin admin) { this.admin = admin;
    }
}
