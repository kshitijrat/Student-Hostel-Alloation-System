package com.kshitijhms.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kshitijhms.hms.entities.Student;
import com.kshitijhms.hms.repo.BranchRepo;
import com.kshitijhms.hms.repo.RoomRepo;
import com.kshitijhms.hms.repo.StudentRepo;

@Controller

public class ProfileController {

    @Autowired
    StudentRepo srepo;
    @Autowired
    BranchRepo brrepo;
    @Autowired
    RoomRepo rrepo;

    @GetMapping("/profile")
    public String displayProfilePage(){
        
        return "profile";
    }

    @PostMapping("/profile")
    public String displayD(@RequestParam("id")int id,Model model){
        Student student = srepo.findStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("br", brrepo.findByStudent(student));
        if(rrepo.findRoomByStudent(student) != null){
            model.addAttribute("roomNo", rrepo.findRoomByStudent(student).getRoomId());
        }else {
            model.addAttribute("roomNo", -1);
        }
        
        return "profile";
    }
    



}
