package edu.tcu.cs.peerevalbackend.seniorDesignTeam.converter;

import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.dto.SeniorDesignTeamDto;
import org.springframework.core.convert.converter.Converter;

public class TeamDtoToTeamConverter implements Converter<SeniorDesignTeamDto, SeniorDesignTeam> {
    @Override
    public SeniorDesignTeam convert(SeniorDesignTeamDto source) {
        SeniorDesignTeam seniorDesignTeam = new SeniorDesignTeam();
        seniorDesignTeam.setName(source.name());
        seniorDesignTeam.setStudents(source.students());
        seniorDesignTeam.setInstructor(source.instructor());
        seniorDesignTeam.setSection(source.section());
        return seniorDesignTeam;
    }
}
