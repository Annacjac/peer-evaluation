package edu.tcu.cs.peerevalbackend.instructor;

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

    //@OneToMany(mappedBy = "instructor")
    //private Set<SeniorDesignTeam> teams = new HashSet<>();

    public Instructor() {}

    // Getters and Setters
}
