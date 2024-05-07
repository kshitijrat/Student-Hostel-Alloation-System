package com.kshitijhms.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kshitijhms.hms.entities.Hostel;
import com.kshitijhms.hms.repo.HostelRepo;
import java.util.*;

@Controller
public class MyController {

    @Autowired
    HostelRepo hostelRepo;

    @RequestMapping("/")
    public String displayLoginPage() {
        // save all hostels
        // Boys hostel(1,2,3,4,5,6,8,9,10,11)
        Hostel hostel = new Hostel(1, "Homi Jahangir Baba Bhavan", "Boys");
        Hostel hostel2 = new Hostel(2, "Vikram Sarabhai Bhavan", "Boys");
        Hostel hostel3 = new Hostel(3, "A.P. Kanivide Bhavan", "Boys");
        Hostel hostel4 = new Hostel(4, "Chandrasekhara Venkat Raman Bhavan", "Boys");
        Hostel hostel5 = new Hostel(5, "Mokshagundam visvesvarvaya Bhavan", "Boys");
        Hostel hostel6 = new Hostel(6, "Jagadish Chandra Bose Bhavan", "Boys");
        Hostel hostel8 = new Hostel(8, "Ramanujan Bhavan", "Boys");
        Hostel hostel9 = new Hostel(9, "Sardar Vallab Bhai Patel Bhavan", "Boys");
        Hostel hostel0 = new Hostel(10, "RamKrishna Param Hans Bhavan", "Boys");
        Hostel hostel11 = new Hostel(11, "Swami Vivekanand Bhavan", "Boys");

        // Girls hostel (7,12)
        Hostel hostel7 = new Hostel(7, "Kalpana Chawla Bhavan", "Girls");
        Hostel hostel12 = new Hostel(12, "Sarasvati Bhavan", "Girls");

        List<Hostel> list = new ArrayList<>();
        list = List.of(hostel, hostel2, hostel3, hostel4, hostel5, hostel6, hostel7, hostel8, hostel9, hostel0,
                hostel11, hostel12);
        hostelRepo.saveAllAndFlush(list);

        return "login";
    }
}
