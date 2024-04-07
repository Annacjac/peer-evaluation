@RestController
@RequestMapping()
public class SeniorDesignTeamController{

    @GetMapping
    public Result findAllTeams{}
    
    @GetMapping
    public Result findByTeamName{}
    
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
