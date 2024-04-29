package edu.tcu.cs.peerevalbackend.student;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

 @Service
 @Transactional
 public class StudentService{

     private final StudentRepository studentRepository;
    
     public StudentService(StudentRepository studentRepository){
         this.studentRepository = studentRepository;
     }
    
 }
