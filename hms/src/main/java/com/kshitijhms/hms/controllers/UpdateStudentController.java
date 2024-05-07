package com.kshitijhms.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kshitijhms.hms.entities.Staff;
import com.kshitijhms.hms.repo.StaffRepo;
import com.kshitijhms.hms.services.StudentServices;

@Controller
public class UpdateStudentController {

    @GetMapping("/updatestudent")
    public String displayDeleteStudentForm() {
        return "updatestudent";
    }

    @Autowired
    StudentServices stdservice;

    @Autowired
    StaffRepo staffrepo;

    // @GetMapping("/deletestudentform")
    // public String deleteStudentForm(
    // @RequestParam("id")int id,Model model){

    // // check if staff is in same hostel as student

    // String msg = stdservice.deleteStudent(id);
    // if(msg.equals("error")){
    // model.addAttribute("error", "Student not exist in your hostel");
    // return "updatestudent";
    // }

    // stdservice.deleteStudent(id);
    // model.addAttribute("success", "Student Deleted Successful!");
    // return "updatestudent";

    // }

    


}
