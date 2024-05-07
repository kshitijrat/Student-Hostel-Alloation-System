package com.kshitijhms.hms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshitijhms.hms.entities.Student;
import com.kshitijhms.hms.repo.StudentRepo;

import jakarta.transaction.Transactional;

@Service
public class StudentServices {
    
    @Autowired
    StudentRepo srepo;

    // check email and password are correct or not
    public boolean isEmailAndPasswordCorrect(String email,String password){
        Student s = srepo.findStudentByEmail(email);
        if(s.getEmail().equals(email) && s.getPassword().equals(password))return true;
        return false;
    }

    // delete student by staff(Wardern & A.Warden)
    @Transactional
    public String deleteStudent(int studentId){
        Student s = new Student();
         s= srepo.findStudentById(studentId);
         if(!srepo.existsById(studentId))return "error";
         srepo.deleteById(studentId);
        
         return "delete";
        
    }



}
