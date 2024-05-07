package com.kshitijhms.hms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitijhms.hms.entities.Extra;
import com.kshitijhms.hms.entities.Student;

public interface ExtraRepo extends JpaRepository<Extra,Integer>{
    Extra findByStudent(Student student);
}
