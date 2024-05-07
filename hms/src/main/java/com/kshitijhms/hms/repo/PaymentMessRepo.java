package com.kshitijhms.hms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitijhms.hms.entities.PaymentMess;
import com.kshitijhms.hms.entities.Student;

public interface PaymentMessRepo extends JpaRepository<PaymentMess, Integer>{

    List<PaymentMess> findByStudent(Student student);
    
}
