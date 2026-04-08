package com.smartcouriersystem.Smart.Courier.Management.System.service;

import com.smartcouriersystem.Smart.Courier.Management.System.DTO.RequestDTO.LocationDTO;
import com.smartcouriersystem.Smart.Courier.Management.System.model.Location;
import com.smartcouriersystem.Smart.Courier.Management.System.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioFileFormat;

@Service
public class LocationService{
    @Autowired
    LocationRepository locationRepository;

    public Location save(LocationDTO dto) {
        Location location = new Location();
        location.setName(dto.getCity());
        location.setPincode(dto.getPincode());
        return locationRepository.save(location);
    }
}
