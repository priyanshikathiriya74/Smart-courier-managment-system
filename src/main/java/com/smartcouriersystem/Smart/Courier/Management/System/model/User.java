package com.smartcouriersystem.Smart.Courier.Management.System.model;

import com.smartcouriersystem.Smart.Courier.Management.System.Enum.UserRole;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String name;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Order> order;

    @OneToMany(mappedBy = "agent",cascade = CascadeType.ALL)
    private List<DeliveryAssignment> agentAssignment;

    @OneToMany(mappedBy = "manager",cascade = CascadeType.ALL)
    private List<DeliveryAssignment> managerAssignment;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

//    public List<Order> getOrder() {
//        return order;
//    }
//
//    public void setOrder(List<Order> order) {
//        this.order = order;
//    }
//
//    public List<DeliveryAssignment> getAgentAssignment() {
//        return agentAssignment;
//    }
//
//    public void setAgentAssignment(List<DeliveryAssignment> agentAssignment) {
//        this.agentAssignment = agentAssignment;
//    }
//
//    public List<DeliveryAssignment> getManagerAssignment() {
//        return managerAssignment;
//    }
//
//    public void setManagerAssignment(List<DeliveryAssignment> managerAssignment) {
//        this.managerAssignment = managerAssignment;
//    }
}
