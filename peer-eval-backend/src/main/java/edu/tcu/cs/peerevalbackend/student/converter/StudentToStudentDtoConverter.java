package edu.tcu.cs.peerevalbackend.student.converter;
 import org.springframework.core.convert.converter.Converter;
 import org.springframework.stereotype.Component;

 import edu.tcu.cs.peerevalbackend.student.Student;
 import edu.tcu.cs.peerevalbackend.student.dto.StudentDto;


 @Component
 public class StudentToStudentDtoConverter implements Converter<Student, StudentDto>{

     private final GroupToGroupDtoConverter groupToGroupDtoConverter;
    
     public StudentToStudentDtoConverter(GroupToGroupDtoConverter groupToGroupDtoConverter){
        this.groupToGroupDtoConverter = groupToGroupDtoConverter;
     }

    @Override
     public StudentDto convert(Student source) {
         StudentDto studentDto = new StudentDto(source.getId(), source.getFirstName(), source.getMidInit(), source.getLastName(), source.getPassword(), source.getGroup() != null ? this.groupToGroupDtoConverter.convert(source.getGroup()) : null);
         return studentDto;
     }
 }
