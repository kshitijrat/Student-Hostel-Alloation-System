package com.kshitijhms.hms.controllers;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kshitijhms.hms.entities.Users;
import com.kshitijhms.hms.repo.UserRepo;

@Controller
public class UserController {

    // display home page
    @GetMapping("/home")
    public String displayHome() {
        return "home";
    }

    // @GetMapping("/redirect-home")
    // public ResponseEntity<String> redirectToHomePage(Model model) {
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.add("Location", "/home"); // Specify the URL of your home.html page

    //     // Return a response entity with status code 3xx and the location header
    //     return ResponseEntity.status(HttpStatus.FOUND).headers(headers).body(null);
    // }

    @Autowired
    UserRepo userRepo;

    // register new user and then go to login page
    @PostMapping("/saveuserdata")
    public String goToLoginPage(@RequestParam("userName") String userName,
            @RequestParam("userEmail") String userEmail,
            @RequestParam("userDob") String userDob, @RequestParam("userPassword") String userPassword,
            @RequestParam("userPassword2") String userPassword2,
            @RequestParam("userCategory") String userCategory,
            Model model) throws NotFoundException, IOException {
        
        // if user is alerady registered
        if (userRepo.findByUserEmail(userEmail) != null) {
            // return ResponseEntity.ok().body(showHtmlContent("templates/home.html"));
            model.addAttribute("error", "This Email is Already Registered!");
            return "home";
        }

        // save user detail
        Users users = new Users();
        users.setUserName(userName);
        users.setUserEmail(userEmail);
        users.setUserDob(userDob);
        users.setUserCategory(userCategory);
        if (userPassword.equals(userPassword2)) {
            users.setUserPassword(userPassword);
            userRepo.save(users);
        }
        //add all tribute into model
        // model.addAttribute("userName", users.getUserName());
        // model.addAttribute("userEmail", users.getUserEmail());
        // model.addAttribute("userDob", users.getUserDob());
        // model.addAttribute("userCategory", users.getUserCategory());
        model.addAttribute("user", users);
        // return to preview.html page
        // return ResponseEntity.ok().body(showHtmlContent("templates/preview.html"));
        return "preview";
    }

    // public String showHtmlContent(String s) throws IOException {
    //     // Load the login HTML file from the resources folder
    //     ClassPathResource resource = new ClassPathResource(s);
    //     byte[] fileBytes = Files.readAllBytes(resource.getFile().toPath());
    //     String htmlContent = new String(fileBytes);
    //     return htmlContent;
    // }

}
