package edu.tcu.cs.peerevalbackend.student;

import edu.tcu.cs.peerevalbackend.section.Section;

import java.util.ArrayList;
import java.util.List;

public class DebuggingTestsWillDelete {
    StudentService studentService = new StudentService();
    List<Student> students;
    Section section;

    public static void main(String[] args){
        DebuggingTestsWillDelete d = new DebuggingTestsWillDelete();

    }

    public DebuggingTestsWillDelete(){
        setup();
        test();
    }
    public void setup(){
        section = new Section();
        section.setSectionName("Section1");
        students = new ArrayList<>();

        Student s1 = new Student();
        s1.setId("1");
        s1.setEmail("student1@gmail.com");
        s1.setFirstName("John");
        s1.setLastName("Doe");
        s1.setPassword("password1");
        s1.setSectionName("Section1");
        students.add(s1);

        Student s2 = new Student();
        s2.setId("2");
        s2.setEmail("student2@gmail.com");
        s2.setFirstName("Jane");
        s2.setLastName("Dou");
        s2.setPassword("password2");
        students.add(s2);

        Student s3 = new Student();
        s3.setId("3");
        s3.setEmail("student3@gmail.com");
        s3.setFirstName("Brian");
        s3.setLastName("Smith");
        s3.setPassword("password3");
        students.add(s3);
    }
    public void test(){
        //System.out.println(students.get(0).getEmail());
        System.out.println(studentService.findByEmail("student1@gmail.com"));
    }
}
