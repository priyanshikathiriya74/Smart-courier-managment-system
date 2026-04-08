package com.smartcouriersystem.Smart.Courier.Management.System.DTO.RequestDTO;

public class LocationDTO {
    private String city;
    private Long pincode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getPincode() {
        return pincode;
    }

    public void  setPincode(long pincode) {
        this.pincode = pincode;
    }
}
