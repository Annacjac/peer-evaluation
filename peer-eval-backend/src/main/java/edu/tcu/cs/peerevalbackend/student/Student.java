package edu.tcu.cs.peerevalbackend.student;

import java.io.Serializable;

import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.ManyToOne;

@Entity
public class Student implements Serializable{
    @Id
    private int id;
    private String email;
    private String firstName;
    private char middleInit;
    private String lastName;
    private String password;

    @ManyToOne
    private SeniorDesignTeam team;

    public Student(){

    }

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public char getMidInit(){
        return middleInit;
    }

    public void setMidInit(char middleInit){
        this.middleInit = middleInit;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

     public SeniorDesignTeam getTeam(){
         return team;
     }

     public void setTeam(SeniorDesignTeam team){
         this.team = team;
     }

}
