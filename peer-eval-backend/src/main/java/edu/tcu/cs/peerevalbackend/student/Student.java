package edu.tcu.cs.peerevalbackend.student;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;

@Entity
public class Student implements Serializable{
    @Id
    private String id;
    private String firstName;
    private char middleInit;
    private String lastName;
    private String password;

    //@ManyToOne
    //private Group team;

    public Student(){

    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
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

    // public Group getGroup(){
    //     return team;
    // }

    // public void setGroup(Group team){
    //     this.team = team;
    // }

}
