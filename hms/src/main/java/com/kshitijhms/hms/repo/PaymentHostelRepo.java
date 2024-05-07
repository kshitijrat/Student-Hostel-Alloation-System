package com.kshitijhms.hms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kshitijhms.hms.entities.Hostel;
import com.kshitijhms.hms.entities.PaymentHostel;
import com.kshitijhms.hms.entities.Student;
import java.util.List;


public interface PaymentHostelRepo extends JpaRepository<PaymentHostel,Integer>{

    @Query("select u from PaymentHostel u where u.student.id =:n") 
    public List<PaymentHostel> findByStudentId(@Param("n")int n);
    
    // public PaymentHostel findByStudent(Student student);
    // PaymentHostel findPaymentByHostel(Hostel Hostel);
    List<PaymentHostel> findByStudent(Student student);
    
}
