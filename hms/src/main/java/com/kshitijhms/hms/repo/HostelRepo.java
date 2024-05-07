package com.kshitijhms.hms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kshitijhms.hms.entities.Hostel;

import jakarta.transaction.Transactional;

public interface HostelRepo extends JpaRepository<Hostel,Integer>{
    Hostel findHostelByHostelId(int hostelId);
   
}
