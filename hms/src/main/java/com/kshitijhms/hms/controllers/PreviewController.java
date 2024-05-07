package com.kshitijhms.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kshitijhms.hms.entities.Extra;
import com.kshitijhms.hms.entities.PaymentHostel;
import com.kshitijhms.hms.entities.PaymentMess;
import com.kshitijhms.hms.entities.Staff;
import com.kshitijhms.hms.entities.Student;
import com.kshitijhms.hms.entities.Users;
import com.kshitijhms.hms.repo.ExtraRepo;
import com.kshitijhms.hms.repo.PaymentHostelRepo;
import com.kshitijhms.hms.repo.PaymentMessRepo;
import com.kshitijhms.hms.repo.StaffRepo;
import com.kshitijhms.hms.repo.StudentRepo;
import com.kshitijhms.hms.repo.UserRepo;


@Controller
public class PreviewController {
    @GetMapping("/preview")
    public String displayPreviewPage(){
        return "preview";
    }

    @Autowired
    StaffRepo staffRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    PaymentHostelRepo payhostelrepo;

    @Autowired
    ExtraRepo extraRepo;

    @Autowired
    PaymentMessRepo paymessrepo;

    @PostMapping("/savepreviewinfo")
    public String saveStaffInfo(@RequestParam("id")int id,
    @RequestParam("userName")String userName, 
    @RequestParam("userEmail")String userEmail,
    @RequestParam("userDob")String userDob,
    @RequestParam("userCategory")String category,
    @RequestParam("gender")String gender,
    @RequestParam("userPhone")String userPhone,
    @RequestParam("userAddress")String userAddress,Model model) throws NotFoundException{
        Users users = userRepo.findByUserEmail(userEmail);
        if(category.trim().equals("Staff")){
            //staff
            Staff staff = new Staff();
            staff.setAddress(userAddress);
            staff.setEmail(userEmail);
            staff.setGender(userAddress);
            staff.setId(id);
            staff.setName(userName);
            staff.setPhone(userPhone);
            staff.setPassword(users.getUserPassword());
            // save staff info
            staffRepo.save(staff);
        }else {
            // student
            Student student = new Student();
            student.setAddress(userAddress);
            student.setEmail(userEmail);
            student.setGender(gender);
            student.setId(id);
            student.setName(userName);
            student.setPhone(userPhone);
            student.setPassword(users.getUserPassword());
            // save student info
            studentRepo.save(student);

            // save payment hostel info
            PaymentHostel paymentHostel  = new PaymentHostel();
            paymentHostel.setStudent(student);;
            paymentHostel.setPaidAmount(0);
            paymentHostel.setRemainAmount(25000);
            paymentHostel.setPayDate("---");
            payhostelrepo.save(paymentHostel);

            //save payment mess info
            PaymentMess paymentMess = new PaymentMess();
            paymentMess.setStudent(student);;
            paymentMess.setPaidAmount(0);
            paymentMess.setRemainAmount(26000);
            paymentMess.setPayDate("---");
            paymessrepo.save(paymentMess);


        }
        return "redirect:/login";
    }

}
