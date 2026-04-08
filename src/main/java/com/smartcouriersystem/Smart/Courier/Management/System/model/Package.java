package com.smartcouriersystem.Smart.Courier.Management.System.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartcouriersystem.Smart.Courier.Management.System.Enum.PackageStatus;
import jakarta.persistence.*;

@Entity
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long packageId;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "orderId")
    private Order order;

    @Enumerated(EnumType.STRING)
    private PackageStatus status;

    @OneToOne(mappedBy = "pkg",cascade = CascadeType.ALL)
    private DeliveryAssignment deliveryAssignment;


    public long getPackageId() {
        return packageId;
    }

    public void setPackageId(long packageId) {
        this.packageId = packageId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PackageStatus getStatus() {
        return status;
    }

    public void setStatus(PackageStatus status) {
        this.status = status;
    }

    public DeliveryAssignment getDeliveryAssignment() {
        return deliveryAssignment;
    }

    public void setDeliveryAssignment(DeliveryAssignment deliveryAssignment) {
        this.deliveryAssignment = deliveryAssignment;
    }
}

