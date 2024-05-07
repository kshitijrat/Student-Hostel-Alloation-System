package com.kshitijhms.hms.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;

import java.util.*;

@Entity
@Table(name = "hostel")
public class Hostel {
    @Id
    private int hostelId;
    private String hostelName;
    private String hostelCategory;
    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL)
    private List<Room> rooms;
    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL)
    private List<PaymentHostel> paymentHostel;
    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL)
    private List<PaymentMess> paymentMess;
    

    public Hostel() {
    }



    public Hostel(int hostelId, String hostelName, String hostelCategory) {
        this.hostelId = hostelId;
        this.hostelName = hostelName;
        this.hostelCategory = hostelCategory;
    }



    public int getHostelId() {
        return hostelId;
    }
    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
    }
    public String getHostelName() {
        return hostelName;
    }
    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }
    public String getHostelCategory() {
        return hostelCategory;
    }
    public void setHostelCategory(String hostelCategory) {
        this.hostelCategory = hostelCategory;
    }



    public List<Room> getRooms() {
        return rooms;
    }



    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }



    public List<PaymentHostel> getPaymentHostel() {
        return paymentHostel;
    }



    public void setPaymentHostel(List<PaymentHostel> paymentHostel) {
        this.paymentHostel = paymentHostel;
    }



    public List<PaymentMess> getPaymentMess() {
        return paymentMess;
    }



    public void setPaymentMess(List<PaymentMess> paymentMess) {
        this.paymentMess = paymentMess;
    }


    

    

    


}
