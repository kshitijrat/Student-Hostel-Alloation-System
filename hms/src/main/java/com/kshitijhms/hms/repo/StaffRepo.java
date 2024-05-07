package com.kshitijhms.hms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitijhms.hms.entities.Staff;

public interface StaffRepo extends JpaRepository<Staff,Integer>{
    
}
