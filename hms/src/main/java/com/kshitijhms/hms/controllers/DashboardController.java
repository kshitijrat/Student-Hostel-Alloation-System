package com.kshitijhms.hms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String displayDashboard(){
        return "redirect:/dashboard";
    }


    
}
