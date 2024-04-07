package edu.tcu.cs.peerevalbackend.student.converter;

import org.springframework.stereotype.Component;

import edu.tcu.cs.peerevalbackend.student.Student;
import edu.tcu.cs.peerevalbackend.student.dto.StudentDto;

import org.springframework.core.convert.converter.Converter;

@Component
public class StudentDtoToStudentConverter implements Converter<StudentDto, Student>{
    @Override
    public Student convert(StudentDto source){
        Student student = new Student();

        return student;
    }
    
}
