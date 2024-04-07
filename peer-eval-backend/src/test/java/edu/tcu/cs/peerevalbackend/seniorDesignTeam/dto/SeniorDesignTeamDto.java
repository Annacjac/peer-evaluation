package seniorDesignTeam.dto;


public record SeniorDesignTeamDto(String name,
                                  List<Student> students,
                                  Instructor instructor,
                                  Section section){

}
