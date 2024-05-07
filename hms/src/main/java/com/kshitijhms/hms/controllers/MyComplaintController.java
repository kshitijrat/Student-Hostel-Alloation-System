package com.kshitijhms.hms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kshitijhms.hms.entities.Complaint;
import com.kshitijhms.hms.repo.ComplaintRepo;
import com.kshitijhms.hms.repo.StudentRepo;

@Controller
public class MyComplaintController {
    
    @GetMapping("/mycomplaintform")
    public String displayMyComplaintForm(){
        return "mycomplaintform";
    }

    @Autowired
    StudentRepo srepo;
    @Autowired
    ComplaintRepo crepo;
    
    @GetMapping("/mycomplaint")
    public String getMyComplaints(@RequestParam("id")int id, Model model){
        
        // if student not exist
        if(!srepo.existsById(id)){
            model.addAttribute("error", "Student Not Exist!");
            return "mycomplaintform";
        }

        List<Complaint> list = crepo.findByStudent(srepo.findStudentById(id));
        model.addAttribute("list", list);
        return "allcomplaints";

    }



}
