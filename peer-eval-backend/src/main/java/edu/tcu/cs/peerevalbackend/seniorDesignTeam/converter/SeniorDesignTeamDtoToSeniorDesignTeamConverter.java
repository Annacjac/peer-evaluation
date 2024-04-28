package edu.tcu.cs.peerevalbackend.seniorDesignTeam.converter;

import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.dto.SeniorDesignTeamDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SeniorDesignTeamDtoToSeniorDesignTeamConverter implements Converter<SeniorDesignTeamDto, SeniorDesignTeam> {
    @Override
    public SeniorDesignTeam convert(SeniorDesignTeamDto source){
        SeniorDesignTeam seniorDesignTeam = new SeniorDesignTeam();
        seniorDesignTeam.setName(source.name());
        seniorDesignTeam.setSection(source.section());
        seniorDesignTeam.setInstructors(source.instructors());
        seniorDesignTeam.setStudents(source.students());
        return seniorDesignTeam;
    }
}
