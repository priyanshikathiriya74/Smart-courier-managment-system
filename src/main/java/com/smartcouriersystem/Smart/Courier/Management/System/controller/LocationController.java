package com.smartcouriersystem.Smart.Courier.Management.System.controller;

import com.smartcouriersystem.Smart.Courier.Management.System.DTO.RequestDTO.LocationDTO;
import com.smartcouriersystem.Smart.Courier.Management.System.model.Location;
import com.smartcouriersystem.Smart.Courier.Management.System.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("location")
public class LocationController {
    @Autowired
    LocationService locationService;

    @PostMapping
    public Location save(@RequestBody LocationDTO dto){
        return locationService.save(dto);
    }
}
