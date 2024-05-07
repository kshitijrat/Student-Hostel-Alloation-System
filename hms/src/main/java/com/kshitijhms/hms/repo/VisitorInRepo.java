package com.kshitijhms.hms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitijhms.hms.entities.VisitorIn;

public interface VisitorInRepo extends JpaRepository<VisitorIn,Integer>{
    VisitorIn findByid(int id);
}
