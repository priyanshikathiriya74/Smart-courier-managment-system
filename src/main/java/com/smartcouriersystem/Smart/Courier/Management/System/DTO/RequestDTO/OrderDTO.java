package com.smartcouriersystem.Smart.Courier.Management.System.DTO.RequestDTO;

public class OrderDTO {
    private long customerId;
    private long pickupLocationId;
    private long deliveryLocationId;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getPickupLocationId() {
        return pickupLocationId;
    }

    public void setPickupLocationId(long pickupLocationId) {
        this.pickupLocationId = pickupLocationId;
    }

    public long getDeliveryLocationId() {
        return deliveryLocationId;
    }

    public void setDeliveryLocationId(long deliveryLocationId) {
        this.deliveryLocationId = deliveryLocationId;
    }
}
