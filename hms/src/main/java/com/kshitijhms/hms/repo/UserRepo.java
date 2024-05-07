package com.kshitijhms.hms.repo;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitijhms.hms.entities.Users;
import java.util.List;


public interface UserRepo extends JpaRepository<Users,Integer>{

   Users findByUserEmail(String userEmail) throws NotFoundException;
    
    
}