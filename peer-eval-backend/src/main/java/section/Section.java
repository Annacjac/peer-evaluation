package section;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import team.SeniorDesignTeam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Section implements Serializable {
    @Id
    private Integer id;

    private String name;

    private String academicYear;



    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "section")
    private List<SeniorDesignTeam> team = new ArrayList<>();




}


