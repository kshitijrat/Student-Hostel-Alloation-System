package com.kshitijhms.hms.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kshitijhms.hms.entities.VisitorIn;
import com.kshitijhms.hms.repo.StudentRepo;
import com.kshitijhms.hms.repo.VisitedRepo;
import com.kshitijhms.hms.repo.VisitorInRepo;
import com.kshitijhms.hms.services.VisitedService;

@Controller
public class VisitorInController {
    @GetMapping("/visitorin")
    public String displayVisitorInForm() {
        return "visitorin";
    }

    @Autowired
    StudentRepo srepo;

    @Autowired
    VisitorInRepo vinrepo;

    @Autowired
    VisitedService vservice;

    @GetMapping("/getvisitreceipt")
    public String getVisitorReceipt(@RequestParam("name") String name,
            @RequestParam("email") String email, @RequestParam("aadhar") String aadhar,
            @RequestParam("id") int id,
            @RequestParam("phone") String phone, @RequestParam("relation") String relation, Model model) {

        // if student not exist by id
        if (!srepo.existsById(id)) {
            model.addAttribute("error", "Student Not Exitst!");
            return "visitorin";
        }

        // if visitor is not check out i.e.ealready visitod
        


        VisitorIn visitorIn = new VisitorIn();
        visitorIn.setAadhar(aadhar);
        visitorIn.setEmail(email);
        visitorIn.setHostel(srepo.findStudentById(id).getHostel());
        visitorIn.setInDate(currDate());
        visitorIn.setName(name);
        visitorIn.setRelation(relation);
        visitorIn.setPhone(aadhar);
        visitorIn.setPhone(phone);
        visitorIn.setOutDate("---");
        visitorIn.setStudent(srepo.findStudentById(id));

        vinrepo.save(visitorIn);

        

        model.addAttribute("msg", "Remember this Id, it is required at time of checkOut");
        model.addAttribute("visitorInfo", visitorIn);

        return "visitorinreceipt";

    }

    // current date
    public String currDate() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Format the current date using the defined format
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
    }
}
