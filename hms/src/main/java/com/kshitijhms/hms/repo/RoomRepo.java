package com.kshitijhms.hms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitijhms.hms.entities.Room;
import com.kshitijhms.hms.entities.Student;

import java.util.List;


public interface RoomRepo extends JpaRepository<Room,Integer>{
    Room findRoomByStudent(Student student);
}
