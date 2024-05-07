package com.kshitijhms.hms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitijhms.hms.entities.Complaint;
import com.kshitijhms.hms.entities.Hostel;
import com.kshitijhms.hms.entities.Student;

public interface ComplaintRepo extends JpaRepository<Complaint,Integer>{
    List<Complaint> findByHostel(Hostel hostel);
    List<Complaint> findByStudent(Student student);
}
