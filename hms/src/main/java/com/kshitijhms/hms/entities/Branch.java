package com.kshitijhms.hms.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String branchstr;
    @OneToOne
    private Student student;
    @ManyToOne
    private Hostel hostel;


    

    public Branch() {
    }

    

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Hostel getHostel() {
        return hostel;
    }
    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }



    public String getBranchstr() {
        return branchstr;
    }



    public void setBranchstr(String branchstr) {
        this.branchstr = branchstr;
    }


    

}
