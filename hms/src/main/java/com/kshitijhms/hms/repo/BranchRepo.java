package com.kshitijhms.hms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitijhms.hms.entities.Branch;
import com.kshitijhms.hms.entities.Student;

public interface BranchRepo extends JpaRepository<Branch,Integer>{
    Branch findByStudent(Student student);
}
