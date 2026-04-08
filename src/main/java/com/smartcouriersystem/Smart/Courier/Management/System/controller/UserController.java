package com.smartcouriersystem.Smart.Courier.Management.System.controller;

import com.smartcouriersystem.Smart.Courier.Management.System.DTO.RequestDTO.UserRequestDTO;
import com.smartcouriersystem.Smart.Courier.Management.System.DTO.ResponseDTO.Response;
import com.smartcouriersystem.Smart.Courier.Management.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("user")
public class UserController{

    @Autowired
    UserService userService;

    @PostMapping("createUser")
    public ResponseEntity<Response> save(@RequestBody UserRequestDTO dto){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response(userService.save(dto),HttpStatus.CREATED,"success"));
        }catch (ResponseStatusException ex){
            return ResponseEntity.status(ex.getStatusCode()).body(new Response(ex.getStatusCode(),ex.getMessage()));
        }
    }

    @GetMapping("admin/getAllUser")
    public ResponseEntity<Response> getAll(){
        try{
            return ResponseEntity.ok(new Response(userService.getAll(),HttpStatus.OK,"success"));
        }catch (ResponseStatusException ex){
            return ResponseEntity.status(ex.getStatusCode()).body(new Response(ex.getStatusCode(),ex.getMessage()));
        }
    }


    @GetMapping("customer/getAllOrder")
    public ResponseEntity<Response> getAllOrder(@RequestParam long customerId){
        try{
            return ResponseEntity.ok(new Response(userService.getAllOrder(customerId),HttpStatus.OK,"Success"));
        }catch (ResponseStatusException ex){
            return ResponseEntity.status(ex.getStatusCode()).body(new Response(ex.getStatusCode(),ex.getMessage()));

        }
    }
}
