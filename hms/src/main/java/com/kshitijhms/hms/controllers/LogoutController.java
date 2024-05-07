package com.kshitijhms.hms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logoutUser() {
        // Perform logout operation here
        // For example, invalidate session or clear authentication
        return "redirect:/login";
    }

}
