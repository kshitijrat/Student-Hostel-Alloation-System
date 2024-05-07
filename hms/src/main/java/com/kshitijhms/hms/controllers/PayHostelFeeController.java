package com.kshitijhms.hms.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.management.remote.SubjectDelegationPermission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kshitijhms.hms.entities.PaymentHostel;
import com.kshitijhms.hms.entities.Student;
import com.kshitijhms.hms.repo.ExtraRepo;
import com.kshitijhms.hms.repo.PaymentHostelRepo;
import com.kshitijhms.hms.repo.RoomRepo;
import com.kshitijhms.hms.repo.StudentRepo;
import com.kshitijhms.hms.services.StudentServices;

@Controller
public class PayHostelFeeController {
    @GetMapping("/payhostelfee")
    public String displayPayHostelFeePage() {
        return "payhostelfee";
    }

    @Autowired
    StudentRepo srepo;

    @Autowired
    PaymentHostelRepo payhostrepo;

    @Autowired
    ExtraRepo exrepo;

    @Autowired
    RoomRepo roomrepo;

    @PostMapping("/payhostelfee")
    public String handlePayHostelFee(@RequestParam("id") int id, @RequestParam("amount") int amount,
            Model model) {

        if (!srepo.existsById(id)) {
            model.addAttribute("error", "Student Id not exist!");
            return "payhostelfee";
        }
        Student student = new Student();
        student = srepo.findStudentById(id);

        // if student not allocate room then first register for allocated hostel
        if (roomrepo.findRoomByStudent(student) == null) {
            model.addAttribute("error", "Plese Firstly Reigster for Hostel and Room");
            return "payhostelfee";
        }

        PaymentHostel paymentHostel = new PaymentHostel();
        // PaymentHostel ph = payhostrepo.findByStudent(student);
        List<PaymentHostel> phlist = payhostrepo.findByStudent(student);
        int totalAmountPaid = 0;
        for (PaymentHostel ph : phlist) {
            totalAmountPaid += ph.getPaidAmount();
        }
        // paymentHostel = phlist.get(phlist.size()-1);
        if (totalAmountPaid == 25000 || amount > 25000 - totalAmountPaid) {
            model.addAttribute("error", "You should pay only " + (25000 - totalAmountPaid) + "");
            return "payhostelfee";
        }
        paymentHostel.setPayDate(date());
        paymentHostel.setStudent(student);
        paymentHostel.setRemainAmount(25000 - (totalAmountPaid + amount));
        paymentHostel.setPaidAmount(amount);
        paymentHostel.setHostel(exrepo.findByStudent(student).getHostel());
        payhostrepo.save(paymentHostel);

        model.addAttribute("name", student.getName());
        model.addAttribute("email",student.getEmail());
        model.addAttribute("date", date());
        model.addAttribute("id", student.getId());
        model.addAttribute("amount", amount);

        return "receipt";

    }

    public String date() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Format the current date using the defined format
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
    }

}
