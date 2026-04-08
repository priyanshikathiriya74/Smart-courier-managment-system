package com.smartcouriersystem.Smart.Courier.Management.System.DTO.ResponseDTO;

import com.smartcouriersystem.Smart.Courier.Management.System.Enum.OrderStatus;

public class CustomerOrderResponseDTO {
    private long orderId;
    private OrderStatus status;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
