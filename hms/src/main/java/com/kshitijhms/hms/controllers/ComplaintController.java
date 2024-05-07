package com.kshitijhms.hms.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kshitijhms.hms.entities.Complaint;
import com.kshitijhms.hms.repo.ComplaintRepo;
import com.kshitijhms.hms.repo.RoomRepo;
import com.kshitijhms.hms.repo.StudentRepo;

@Controller
public class ComplaintController {
    @GetMapping("/complaint")
    public String displayComplaintForm() {
        return "complaint";
    }

    @GetMapping("/allcomplaints")
    public String displayAllComplaint() {
        return "allcomplaints";
    }

    @Autowired
    ComplaintRepo crepo;

    @Autowired
    StudentRepo srepo;

    @Autowired
    RoomRepo roomRepo;

    @PostMapping("/complaint")
    public String getComplaint(
            @RequestParam("id") int id, @RequestParam("cType") String ctype,
            @RequestParam("description") String description, @RequestParam("hostelId") int hostelId, Model model) {

        // if student not exist
        if (!srepo.existsById(id)) {
            model.addAttribute("error", "Student Not Exist!");
            return "complaint";
        }

        // if studetn not register for hostel and room
        if (roomRepo.findRoomByStudent(srepo.findStudentById(id)) == null) {
            model.addAttribute("error", "!Please Register for Hostel & Room Allotement");
            return "complaint";
        }

        Complaint complaint = new Complaint();
        complaint.setStudent(srepo.findStudentById(id));
        complaint.setHostel(srepo.findStudentById(id).getHostel());
        complaint.setName(srepo.findStudentById(id).getName());
        complaint.setcType(ctype);
        complaint.setDescription(description);
        complaint.setDate(currDate());
        complaint.setStatus("Pending");
        crepo.save(complaint);

        // List<Complaint> list = crepo.findByStudent(complaint.getStudent());
        
        model.addAttribute("list", complaint);

        return "allcomplaints";

    }

    public String currDate() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String s = localDate.format(dateTimeFormatter);
        return s;
    }

}
