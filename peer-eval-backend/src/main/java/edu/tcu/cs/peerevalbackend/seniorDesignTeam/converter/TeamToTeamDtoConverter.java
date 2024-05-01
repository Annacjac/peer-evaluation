package edu.tcu.cs.peerevalbackend.seniorDesignTeam.converter;

import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.dto.SeniorDesignTeamDto;
import org.springframework.core.convert.converter.Converter;

public class TeamToTeamDtoConverter implements Converter<SeniorDesignTeam, SeniorDesignTeamDto> {
    @Override
    public SeniorDesignTeamDto convert(SeniorDesignTeam source) {
        SeniorDesignTeamDto seniorDesignTeamDto = new SeniorDesignTeamDto(source.getName(),
                source.getStudents(),
                source.getInstructors(),
                source.getSection());
        return seniorDesignTeamDto;
    }
}
