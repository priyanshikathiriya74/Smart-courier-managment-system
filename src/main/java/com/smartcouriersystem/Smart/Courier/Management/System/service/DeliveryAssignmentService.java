package com.smartcouriersystem.Smart.Courier.Management.System.service;

import com.smartcouriersystem.Smart.Courier.Management.System.DTO.RequestDTO.PickupRequestDTO;
import com.smartcouriersystem.Smart.Courier.Management.System.DTO.RequestDTO.PkgAssignmentRequestDTO;
import com.smartcouriersystem.Smart.Courier.Management.System.DTO.ResponseDTO.AgentMonitoringDTO;
import com.smartcouriersystem.Smart.Courier.Management.System.Enum.OrderStatus;
import com.smartcouriersystem.Smart.Courier.Management.System.Enum.PackageStatus;
import com.smartcouriersystem.Smart.Courier.Management.System.Enum.UserRole;
import com.smartcouriersystem.Smart.Courier.Management.System.Exception.PackageNotFoundException;
import com.smartcouriersystem.Smart.Courier.Management.System.Exception.UserNotFoundException;
import com.smartcouriersystem.Smart.Courier.Management.System.model.DeliveryAssignment;
import com.smartcouriersystem.Smart.Courier.Management.System.model.Order;
import com.smartcouriersystem.Smart.Courier.Management.System.model.Package;
import com.smartcouriersystem.Smart.Courier.Management.System.model.User;
import com.smartcouriersystem.Smart.Courier.Management.System.repository.DeliveryAssignmentRepository;
import com.smartcouriersystem.Smart.Courier.Management.System.repository.OrderRepository;
import com.smartcouriersystem.Smart.Courier.Management.System.repository.PackageRepository;
import com.smartcouriersystem.Smart.Courier.Management.System.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class DeliveryAssignmentService {

    @Autowired
    DeliveryAssignmentRepository deliveryAssignmentRepository;
    @Autowired
    PackageRepository packageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;

    public String save(PkgAssignmentRequestDTO dto) {
        for(Long pkgId : dto.getPackageIds()){
            Package pkg = packageRepository.findById(pkgId).orElseThrow(()->new PackageNotFoundException(pkgId));
            User customer = pkg.getOrder().getCustomer();
            pkg.setStatus(PackageStatus.ASSIGNED);
            packageRepository.save(pkg);

            Order order = pkg.getOrder();
            order.setStatus(OrderStatus.ASSIGNED);
            orderRepository.save(order);

            User manager = userRepository.findById(dto.getManagerId()).orElseThrow(()->new UserNotFoundException(dto.getManagerId()));
            if(manager.getRole()!= UserRole.MANAGER){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Only Manager Can Assign Pacakge to Agent");
            }

            User agent = userRepository.findById(dto.getAgentId()).orElseThrow(()->new UserNotFoundException(dto.getAgentId()));
            if(agent.getRole()!=UserRole.AGENT){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,"We can assign packages to only agents");
            }

            DeliveryAssignment dAss = new DeliveryAssignment();
            dAss.setAssignedAt(LocalDateTime.now());
            dAss.setManager(manager);
            dAss.setAgent(agent);
            dAss.setCustomer(customer);
            dAss.setPkg(pkg);

            deliveryAssignmentRepository.save(dAss);
        }
        return "All packages Assigned Successfully to Agent.";
    }

    @Transactional
    public String setPickup(PickupRequestDTO dto) {
        DeliveryAssignment dAss = deliveryAssignmentRepository.findByAgentIdAndPickupId(dto.getAgentId(),dto.getPackageId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"there no agent with this package"));
        Order order = dAss.getPkg().getOrder();
        order.setStatus(OrderStatus.IN_TRANSIT);
        orderRepository.save(order);

        Package pkg = dAss.getPkg();
        pkg.setStatus(PackageStatus.IN_TRANSIT);
        packageRepository.save(pkg);
        dAss.setPickupAt(LocalDateTime.now());
         deliveryAssignmentRepository.save(dAss);
        return "Agent pickup package";
    }

    @Transactional
    public String delivered(PickupRequestDTO dto) {
        DeliveryAssignment dAss = deliveryAssignmentRepository.findByAgentIdAndPickupId(dto.getAgentId(),dto.getPackageId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"there no agent with this package"));
        Order order = dAss.getPkg().getOrder();
        order.setStatus(OrderStatus.DELIVERED);
        orderRepository.save(order);

        Package pkg = dAss.getPkg();
        pkg.setStatus(PackageStatus.DELIVERED);
        packageRepository.save(pkg);
        dAss.setDeliveryAt(LocalDateTime.now());
        deliveryAssignmentRepository.save(dAss);
        return "Agent Delivered Parcel Successfully";
    }

    public List<User> getAllAgent() {
        List<User> agents = userRepository.findAllAgent();
        return agents;
    }
}
