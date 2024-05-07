package com.kshitijhms.hms.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.kshitijhms.hms.entities.Visited;
import com.kshitijhms.hms.entities.VisitorIn;
import com.kshitijhms.hms.repo.VisitedRepo;
import com.kshitijhms.hms.repo.VisitorInRepo;

@Controller
public class VisitorOutControlle {
    
    @GetMapping("/visitorout")
    public String displayVisitorOutForm(){
        return "visitorout";
    }

    @Autowired
    VisitorInRepo vinrepo;

    @Autowired
    VisitedRepo vrepo;

    // update visitorin out_date
    @GetMapping("/visitout")
    public String visisOut(@RequestParam("id")int id,@RequestParam("email")String email,
    Model model){
        VisitorIn visitorIn = new VisitorIn();
        if(!vinrepo.existsById(id)){
            model.addAttribute("error", "Visitor Id Invalid!");
            return "visitorout";
        }
        visitorIn = vinrepo.findByid(id);
        visitorIn.setOutDate(currDate());
        vinrepo.save(visitorIn);

        //save visited
        Visited visited = new Visited();
        visited.setInDate(visitorIn.getInDate());
        visited.setOutDate(visitorIn.getOutDate());
        visited.setVisitor(visitorIn);
        vrepo.save(visited);

        model.addAttribute("success", "Thanks for visitin out hostel!");
        return "visitorout";
    }

    public String currDate(){
         // Get the current date
         LocalDate currentDate = LocalDate.now();

         // Define the desired date format
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
 
         // Format the current date using the defined format
         String formattedDate = currentDate.format(formatter);
         return formattedDate;
    }

}
