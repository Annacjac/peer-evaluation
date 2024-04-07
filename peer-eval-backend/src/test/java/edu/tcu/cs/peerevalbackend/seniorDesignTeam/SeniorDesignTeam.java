package seniorDesignTeam;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
    public class SeniorDesignTeam implements Serializable {
        @Id
        private String name;

        @OneToMany(mappedBy="name", CascadeType.PERSIST, CascadeType.MERGE)
        private List<Student> students = new ArrayList<>();

        @OneToOne
        private Instructor instructor;

        @ManyToOne
        private Section section;

        public SeniorDesignTeam(){
        }

    }
