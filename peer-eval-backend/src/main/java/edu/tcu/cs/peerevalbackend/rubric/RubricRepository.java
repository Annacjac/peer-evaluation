package edu.tcu.cs.peerevalbackend.rubric;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RubricRepository extends JpaRepository<Rubric, Integer> {
    Rubric findByName(String name);


}

