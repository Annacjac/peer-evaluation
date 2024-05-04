package edu.tcu.cs.peerevalbackend.student.converter;
 import edu.tcu.cs.peerevalbackend.seniorDesignTeam.converter.SeniorDesignTeamDtoToSeniorDesignTeamConverter;
 import edu.tcu.cs.peerevalbackend.seniorDesignTeam.converter.SeniorDesignTeamtoSeniorDesignTeamDtoConverter;
 import edu.tcu.cs.peerevalbackend.seniorDesignTeam.converter.TeamDtoToTeamConverter;
 import edu.tcu.cs.peerevalbackend.seniorDesignTeam.converter.TeamToTeamDtoConverter;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.core.convert.converter.Converter;
 import org.springframework.stereotype.Component;

 import edu.tcu.cs.peerevalbackend.student.Student;
 import edu.tcu.cs.peerevalbackend.student.dto.StudentDto;
 import edu.tcu.cs.peerevalbackend.seniorDesignTeam.*;


 @Component
 public class StudentToStudentDtoConverter implements Converter<Student, StudentDto>{

     private final SeniorDesignTeamtoSeniorDesignTeamDtoConverter seniorDesignTeamtoSeniorDesignTeamDtoConverter;
     private final SeniorDesignTeamDtoToSeniorDesignTeamConverter seniorDesignTeamDtoToSeniorDesignTeamConverter;

     public StudentToStudentDtoConverter( SeniorDesignTeamtoSeniorDesignTeamDtoConverter seniorDesignTeamtoSeniorDesignTeamDtoConverter, SeniorDesignTeamDtoToSeniorDesignTeamConverter seniorDesignTeamDtoToSeniorDesignTeamConverter) {
         this.seniorDesignTeamtoSeniorDesignTeamDtoConverter = seniorDesignTeamtoSeniorDesignTeamDtoConverter;
         this.seniorDesignTeamDtoToSeniorDesignTeamConverter = seniorDesignTeamDtoToSeniorDesignTeamConverter;
     }

     @Override
     public StudentDto convert(Student source) {
         StudentDto studentDto = new StudentDto(source.getId(), source.getEmail(), source.getFirstName(), source.getMidInit(), source.getLastName(), source.getPassword(), source.getTeam() != null ? this.seniorDesignTeamtoSeniorDesignTeamDtoConverter.convert(source.getTeam()) : null);
         return studentDto;
     }
    
 }
