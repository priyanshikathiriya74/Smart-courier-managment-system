package com.smartcouriersystem.Smart.Courier.Management.System.service;

import com.smartcouriersystem.Smart.Courier.Management.System.DTO.RequestDTO.UserRequestDTO;
import com.smartcouriersystem.Smart.Courier.Management.System.DTO.ResponseDTO.CustomerOrderResponseDTO;
import com.smartcouriersystem.Smart.Courier.Management.System.Enum.UserRole;
import com.smartcouriersystem.Smart.Courier.Management.System.Exception.UserNotFoundException;
import com.smartcouriersystem.Smart.Courier.Management.System.model.Order;
import com.smartcouriersystem.Smart.Courier.Management.System.model.User;
import com.smartcouriersystem.Smart.Courier.Management.System.repository.OrderRepository;
import com.smartcouriersystem.Smart.Courier.Management.System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;

    public User save(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        String role = dto.getRole().toUpperCase();
        user.setRole(UserRole.valueOf(role));
        return userRepository.save(user);
    }

    public List<User> getAll() {
        List<User> list = userRepository.findAll();
        List<User> ans = new ArrayList<>();
        for(User l : list){
            if(l.getRole()!=UserRole.ADMIN){
                ans.add(l);
            }
        }
       return ans;
    }

    public List<CustomerOrderResponseDTO> getAllOrder(long customerId) {
        if(!userRepository.existsById(customerId)){
            throw new UserNotFoundException(customerId);
        }

        List<Order> orders = orderRepository.findByCustomerId(customerId)
                        .orElseThrow(()-> new ResponseStatusException(HttpStatus
                                .NOT_FOUND,"No record found for %d user ".formatted(customerId)));

        List<CustomerOrderResponseDTO> ans = new ArrayList<>();
        for(Order ord : orders){
            CustomerOrderResponseDTO dto = new CustomerOrderResponseDTO();
            dto.setOrderId(ord.getOrderId());
            dto.setStatus(ord.getStatus());
            ans.add(dto);
        }
        return ans;
    }
}
