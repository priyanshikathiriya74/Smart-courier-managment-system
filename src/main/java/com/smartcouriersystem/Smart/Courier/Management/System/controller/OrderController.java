package com.smartcouriersystem.Smart.Courier.Management.System.controller;

import com.smartcouriersystem.Smart.Courier.Management.System.DTO.RequestDTO.OrderDTO;
import com.smartcouriersystem.Smart.Courier.Management.System.DTO.ResponseDTO.Response;
import com.smartcouriersystem.Smart.Courier.Management.System.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;


    @PostMapping("placeOrder")
    public ResponseEntity<Response> save(@RequestBody OrderDTO dto){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response(orderService.save(dto),HttpStatus.CREATED,"Success"));
        }catch(ResponseStatusException ex){
            return ResponseEntity.status(ex.getStatusCode()).body(new Response(ex.getStatusCode(),ex.getMessage()));
        }
    }


}
