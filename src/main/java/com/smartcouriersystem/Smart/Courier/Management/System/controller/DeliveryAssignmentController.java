package com.smartcouriersystem.Smart.Courier.Management.System.controller;

import com.smartcouriersystem.Smart.Courier.Management.System.DTO.RequestDTO.PickupRequestDTO;
import com.smartcouriersystem.Smart.Courier.Management.System.DTO.RequestDTO.PkgAssignmentRequestDTO;
import com.smartcouriersystem.Smart.Courier.Management.System.DTO.ResponseDTO.Response;
import com.smartcouriersystem.Smart.Courier.Management.System.service.DeliveryAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("deliveryAssignment")
public class DeliveryAssignmentController {
    @Autowired
    DeliveryAssignmentService deliveryAssignmentService;

    @PostMapping("packagesAssign")
    public ResponseEntity<Response> pkgAssign(@RequestBody PkgAssignmentRequestDTO dto){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response(deliveryAssignmentService.save(dto),HttpStatus.CREATED,"Success"));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(new Response(e.getStatusCode(),e.getMessage()));
        }
    }

    @PatchMapping("agent/pickup")
    public ResponseEntity<Response> setPickup(@RequestBody PickupRequestDTO dto){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(new Response(deliveryAssignmentService.setPickup(dto),HttpStatus.OK,"Success"));
        }catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).body(new Response(e.getStatusCode(),e.getMessage()));

        }
    }

    @PatchMapping("agent/delivered")
    public ResponseEntity<Response> delivered(@RequestBody PickupRequestDTO dto){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(new Response(deliveryAssignmentService.delivered(dto),HttpStatus.OK,"Success"));
        }catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).body(new Response(e.getStatusCode(),e.getMessage()));

        }
    }

    @GetMapping("manager/getAllAgent")
    public ResponseEntity<Response> getAllAgent(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(new Response(deliveryAssignmentService.getAllAgent(),HttpStatus.OK,"Success"));
        }catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).body(new Response(e.getStatusCode(),e.getMessage()));

        }
    }
}
