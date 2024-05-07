package com.kshitijhms.hms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    private int id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String gender;
    private String address;
    @OneToOne
    private Users usres;
    @OneToOne
    private Hostel hostel;



    public Staff() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Users getUsres() {
        return usres;
    }


    public void setUsres(Users usres) {
        this.usres = usres;
    }


    public Hostel getHostel() {
        return hostel;
    }


    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }


   


    
}
