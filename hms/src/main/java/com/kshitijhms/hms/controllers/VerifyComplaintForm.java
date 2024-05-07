package com.kshitijhms.hms.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kshitijhms.hms.entities.Complaint;
import com.kshitijhms.hms.repo.ComplaintRepo;
import com.kshitijhms.hms.repo.StudentRepo;

@Controller
public class VerifyComplaintForm {
    
    @Autowired
    ComplaintRepo crepo;
    @Autowired
    StudentRepo srepo;

    @GetMapping("/totalcomplaints")
    public String displayTotalComplaints(Model model){
        List<Complaint> list = crepo.findAll();
        model.addAttribute("list", list);
        return "totalcomplaints";
    }


    @PostMapping("/verifycomplaint")
    public String verifyComplaintForm(@RequestParam("id")int id,Model Model){
        Complaint complaint = new Complaint();
        List<Complaint> list = crepo.findByStudent(srepo.findStudentById(id));
        for(Complaint c: list){
            c.setStatus("Accepted");
            crepo.save(c);
        }
        return "totalcomplaints";
    }

}
