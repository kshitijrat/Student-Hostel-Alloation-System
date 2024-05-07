package com.kshitijhms.hms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitijhms.hms.entities.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    Student findStudentById(int id);
    Student findStudentByEmail(String email);
}
