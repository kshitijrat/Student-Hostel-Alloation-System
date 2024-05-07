package com.kshitijhms.hms.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kshitijhms.hms.entities.PaymentMess;
import com.kshitijhms.hms.entities.Room;
import com.kshitijhms.hms.entities.Student;
import com.kshitijhms.hms.repo.ExtraRepo;
import com.kshitijhms.hms.repo.PaymentMessRepo;
import com.kshitijhms.hms.repo.RoomRepo;
import com.kshitijhms.hms.repo.StudentRepo;

@Controller
public class PayMessFeeController {
    @GetMapping("/paymessfee")
    public String displayPayMessFee(){
        return "paymessfee";
    }

    @Autowired
    StudentRepo srepo;

    @Autowired
    PaymentMessRepo pmrepo;

    @Autowired
    ExtraRepo exrepo;

    @Autowired
    RoomRepo roomrepo;

    @PostMapping("/paymessfee")
    public String handlePayHostelFee(@RequestParam("id")int id,@RequestParam("amount")int amount,
    Model model){

        if(!srepo.existsById(id)){
            model.addAttribute("error", "Student Id not exist!");
            return "paymessfee";
        }
        Student student = new Student();
        student = srepo.findStudentById(id);

        // if student not allocate room then first register for allocated hostel
        if(roomrepo.findRoomByStudent(student)==null){
            model.addAttribute("error", "Plese Firstly Reigster for Hostel and Room");
            return "paymessfee";
        }

        PaymentMess paymentMess= new PaymentMess();
        // PaymentHostel ph = payhostrepo.findByStudent(student);
        List<PaymentMess> pmlist = pmrepo.findByStudent(student);
        int totalAmountPaid = 0;
        for(PaymentMess pm : pmlist){
            totalAmountPaid += pm.getPaidAmount();
        }
        // paymentHostel = phlist.get(phlist.size()-1);
        if(totalAmountPaid == 26000 || amount > 26000-totalAmountPaid){
            model.addAttribute("error", "You should pay only "+(26000-totalAmountPaid)+"");
            return "paymessfee";
        }
        paymentMess.setPayDate(date());
        paymentMess.setStudent(student);
        paymentMess.setRemainAmount(26000-(totalAmountPaid+amount));
        paymentMess.setPaidAmount(amount);
        paymentMess.setHostel(exrepo.findByStudent(student).getHostel());
        pmrepo.save(paymentMess);
        
        model.addAttribute("name", student.getName());
        model.addAttribute("email",student.getEmail());
        model.addAttribute("date", date());
        model.addAttribute("id", student.getId());
        model.addAttribute("amount", amount);

        return "receipt";

    }

    public String date(){
         // Get the current date
         LocalDate currentDate = LocalDate.now();

         // Define the desired date format
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
 
         // Format the current date using the defined format
         String formattedDate = currentDate.format(formatter);
         return formattedDate;
    }
    
}
