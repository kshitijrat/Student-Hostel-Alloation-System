package com.kshitijhms.hms.controllers;

import java.sql.Struct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kshitijhms.hms.entities.Student;
import com.kshitijhms.hms.entities.Users;
import com.kshitijhms.hms.repo.StudentRepo;
import com.kshitijhms.hms.repo.UserRepo;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String displayLogin(){
        return "login";
    }


    @Autowired
    UserRepo userRepo;
    @Autowired
    StudentRepo srepo;

    // after login , user go to dashboard
    @GetMapping("/loginuser")
    public String goToDashBoardPage(@RequestParam("userPassword")String userPassword, 
    @RequestParam("userEmail")String userEmail, Model model)
    throws NotFoundException{

    
        

        Users users = new Users();
        users = userRepo.findByUserEmail(userEmail);
        // Student student = new Student();
        // if(users.getUserCategory().equals("Student")){
        //     student = srepo.findStudentByEmail(userEmail);
        // }
        

        // if user not exist then return to login
        if(users == null){
            model.addAttribute("error", "Your are not registered yet!");
            return "login";
        }

        // if user is exist ,check email and passwor is correct or not
        String email = userEmail.trim();
        String password = userPassword.trim();
        if(email.equals(users.getUserEmail()) && password.equals(users.getUserPassword())){
            if(users.getUserCategory().equals("Staff"))return "staffdashboard";
            else {
                model.addAttribute("id", srepo.findStudentByEmail(email).getId());
                return "dashboard";
            }
        }else {
            model.addAttribute("error", "*Bad Credentials");
            return "login";
        }

        

    }

}
