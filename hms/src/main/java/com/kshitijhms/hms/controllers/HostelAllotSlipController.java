package com.kshitijhms.hms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.service.annotation.GetExchange;

@Controller
public class HostelAllotSlipController {
    @GetMapping("/hostelallotslip")
    public String displayHostelAllotSlip(){
        return "hostelallotslip";
    }
    @GetMapping("/hostelreceipt")
    public String displayHostelReceipt(){
        return "hostelreceipt";
    }
}
