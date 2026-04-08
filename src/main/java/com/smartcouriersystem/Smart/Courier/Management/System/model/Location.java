package com.smartcouriersystem.Smart.Courier.Management.System.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Location {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String name;
        private long pincode;

        @OneToMany(mappedBy = "pickupLocation",cascade = CascadeType.ALL)
        private List<Order> pickupOrders;

        @OneToMany(mappedBy = "deliveryLocation",cascade = CascadeType.ALL)
        private List<Order> deliveryOrders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPincode() {
        return pincode;
    }

    public void setPincode(long pincode) {
        this.pincode = pincode;
    }

//    public List<Order> getPickupOrders() {
//        return pickupOrders;
//    }
//
//    public void setPickupOrders(List<Order> pickupOrders) {
//        this.pickupOrders = pickupOrders;
//    }
//
//    public List<Order> getDeliveryOrders() {
//        return deliveryOrders;
//    }
//
//    public void setDeliveryOrders(List<Order> deliveryOrders) {
//        this.deliveryOrders = deliveryOrders;
//    }
}
