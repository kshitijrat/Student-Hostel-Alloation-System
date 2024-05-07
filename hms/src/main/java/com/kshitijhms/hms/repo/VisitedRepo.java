package com.kshitijhms.hms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kshitijhms.hms.entities.Visited;
import com.kshitijhms.hms.entities.VisitorIn;
import java.util.List;


public interface VisitedRepo extends JpaRepository<Visited,Integer>{
    // Visited findVisitedByVisitorIn(VisitorIn visitor);
}
