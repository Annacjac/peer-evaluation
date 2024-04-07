@RestController
@RequestMapping()
public class SeniorDesignTeamController{

    @GetMapping
    public Result findAllTeams{}
    
    @GetMapping("/{teamName}")
    public Result findByTeamName(@PathVariable String teamName){
        return this.SeniorDesignTeamRepository.findByTeamName(teamName)
                .orElseThrow(()->new ArtifactNotFoundException());
    }
    }
    
    @GetMapping
    public Result findByStudent{}
    
    @GetMapping
    public Result findByInstructor{}
    
    @PostMapping
    public Result addTeam{}
    
    @DeleteMapping
    public Result deleteTeam{}
    
    @DeleteMapping
    public Result removeStudent{}
    
    @PostMapping
    public Result addStudent{}
    
    @GetMapping
    public Result findBySection{}
}
