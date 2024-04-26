package edu.tcu.cs.peerevalbackend.instructor;

import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;

    @OneToMany(mappedBy = "instructor")
    private Set<SeniorDesignTeam> teams = new HashSet<>();

    public Instructor() {}

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    //get last name
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // Getters and Setters
}
