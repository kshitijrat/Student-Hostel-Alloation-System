package com.kshitijhms.hms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaffProfileController {
    @GetMapping("/staffprofile")
    public String displayStaffProfile(){
        return "staffprofile";
    }
    
}
