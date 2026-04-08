package com.smartcouriersystem.Smart.Courier.Management.System.service;

import com.smartcouriersystem.Smart.Courier.Management.System.DTO.RequestDTO.OrderDTO;
import com.smartcouriersystem.Smart.Courier.Management.System.Enum.OrderStatus;
import com.smartcouriersystem.Smart.Courier.Management.System.Enum.PackageStatus;
import com.smartcouriersystem.Smart.Courier.Management.System.Enum.UserRole;
import com.smartcouriersystem.Smart.Courier.Management.System.Exception.LocationNotFoundException;
import com.smartcouriersystem.Smart.Courier.Management.System.Exception.UserNotFoundException;
import com.smartcouriersystem.Smart.Courier.Management.System.model.Location;
import com.smartcouriersystem.Smart.Courier.Management.System.model.Order;
import com.smartcouriersystem.Smart.Courier.Management.System.model.Package;
import com.smartcouriersystem.Smart.Courier.Management.System.model.User;
import com.smartcouriersystem.Smart.Courier.Management.System.repository.LocationRepository;
import com.smartcouriersystem.Smart.Courier.Management.System.repository.OrderRepository;
import com.smartcouriersystem.Smart.Courier.Management.System.repository.PackageRepository;
import com.smartcouriersystem.Smart.Courier.Management.System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    PackageRepository packageRepository;

    public Order save(OrderDTO dto) {
       User customer = userRepository.findById(dto.getCustomerId())
               .orElseThrow(()->new UserNotFoundException(dto.getCustomerId()));

       if(customer.getRole()!= UserRole.CUSTOMER){
           throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Only Customer can place order");
       }
       Location pickupLocation = locationRepository.findById(dto.getPickupLocationId())
               .orElseThrow(()->new LocationNotFoundException(dto.getPickupLocationId()));

       Location deliveryLocation = locationRepository.findById(dto.getDeliveryLocationId())
               .orElseThrow(()->new LocationNotFoundException(dto.getDeliveryLocationId()));

       Order order = new Order();
       order.setCustomer(customer);
       order.setPickupLocation(pickupLocation);
       order.setDeliveryLocation(deliveryLocation);
       order.setStatus(OrderStatus.PLACED);
        Package pkg = new Package();
        pkg.setOrder(order);
        pkg.setStatus(PackageStatus.PENDING);
        order.setPackages(pkg);
        orderRepository.save(order);
        packageRepository.save(pkg);
       return order;
    }
}
