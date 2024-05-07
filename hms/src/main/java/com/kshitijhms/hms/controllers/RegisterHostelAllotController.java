package com.kshitijhms.hms.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kshitijhms.hms.entities.Branch;
import com.kshitijhms.hms.entities.Extra;
import com.kshitijhms.hms.entities.Hostel;
import com.kshitijhms.hms.entities.PaymentHostel;
import com.kshitijhms.hms.entities.PaymentMess;
import com.kshitijhms.hms.entities.Room;
import com.kshitijhms.hms.entities.Student;
import com.kshitijhms.hms.repo.BranchRepo;
import com.kshitijhms.hms.repo.ExtraRepo;
import com.kshitijhms.hms.repo.HostelRepo;
import com.kshitijhms.hms.repo.PaymentHostelRepo;
import com.kshitijhms.hms.repo.PaymentMessRepo;
import com.kshitijhms.hms.repo.RoomRepo;
import com.kshitijhms.hms.repo.StudentRepo;
import com.kshitijhms.hms.repo.UserRepo;
import com.kshitijhms.hms.services.StudentServices;

@Controller
public class RegisterHostelAllotController {
    @GetMapping("/registerhostelallot")
    public String displayRegisterHostelAllot() {
        return "registerhostelallot";
    }


    @Autowired
    StudentRepo studentRepo;
    @Autowired
    StudentServices studentServices;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoomRepo roomRepo;
    @Autowired
    HostelRepo hostelRepo;
    @Autowired
    ExtraRepo extraRepo;
    @Autowired
    PaymentHostelRepo phrepo;
    @Autowired
    PaymentMessRepo pmrepo;
    @Autowired
    BranchRepo brrepo;

    // Generate hostel allotement and payment of hostel and room allotement
    @PostMapping("/generatehostelallot")
    public String generateHostelAllotSlip(@RequestParam("id") int id,
            @RequestParam("email") String email, @RequestParam("password") String password,
            @RequestParam("hostelId") int hostelId, 
            @RequestParam("branch")String branch, Model model) {

        // if student not exist by id
        if (!studentRepo.existsById(id)) {
            model.addAttribute("error", "Student Not Exist!");
            return "registerhostelallot";
        }
        // if email and password are not correct
        if (!studentServices.isEmailAndPasswordCorrect(email, password)) {
            model.addAttribute("error", "*Bad Credentials");
            return "registerhostelallot";
        }

        // if student already register for hostel
        if(roomRepo.findRoomByStudent(studentRepo.findStudentById(id)) != null){
            model.addAttribute("error", "You are already register for hostel and room!");
            return "registerhostelallot";
        }

        // save room info and update student
        Room room = new Room();
        Hostel hostel = new Hostel();
        hostel = hostelRepo.findHostelByHostelId(hostelId);
        room.setHostel(hostel);
        Student student = studentRepo.findStudentById(id);
        room.setStudent(student);
        roomRepo.save(room);
        student.setHostel(hostel);
        studentRepo.save(student);

        // set and save extra info
        Extra extra = new Extra();
        extra.setHostel(hostel);
        extra.setStudent(student);
        extraRepo.save(extra);
        
        // update payment hostel
        List<PaymentHostel> listpayhostel = phrepo.findByStudent(student);
        PaymentHostel paymentHostel =listpayhostel.get(listpayhostel.size()-1);
        paymentHostel.setHostel(extraRepo.findByStudent(student).getHostel());
        phrepo.save(paymentHostel);

        // update payment mess
        List<PaymentMess> listpaymess = pmrepo.findByStudent(student);
        PaymentMess paymentMess = listpaymess.get(listpaymess.size()-1);
        paymentMess.setHostel(extraRepo.findByStudent(student).getHostel());
        pmrepo.save(paymentMess);
        

         // save branch
         Branch brch = new Branch();
         brch.setBranchstr(branch);
         brch.setHostel(hostel);
         brch.setStudent(student);
         brrepo.save(brch);

        // ---------
        model.addAttribute("id", id);
        model.addAttribute("name", student.getName());
        model.addAttribute("hostelId", hostelId);
        model.addAttribute("roomId", room.getRoomId());
        model.addAttribute("date", todayDate());
        model.addAttribute("payStatus", "Unpaid");
        model.addAttribute("email", email);
        model.addAttribute("note", "Pay Fee within a week, otherwise fine will be deduct!");
        model.addAttribute("payFeemsg", "Pay Fee via link");
        model.addAttribute("clickhere", "Click Here");

       

        return "hostelallotslip";
    }

    // get current date
    public String todayDate() {
        LocalDate currentDate = LocalDate.now();

        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Format the current local date using the formatter
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
    }

}
