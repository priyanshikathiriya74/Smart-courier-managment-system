package com.smartcouriersystem.Smart.Courier.Management.System.service;

import com.smartcouriersystem.Smart.Courier.Management.System.DTO.RequestDTO.PickupRequestDTO;
import com.smartcouriersystem.Smart.Courier.Management.System.model.DeliveryAssignment;
import com.smartcouriersystem.Smart.Courier.Management.System.repository.DeliveryAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DeliverySchedularService {

    @Autowired
    DeliveryAssignmentService deliveryAssignmentService;

    private final DeliveryAssignmentRepository repository;
    private final ExecutorService executorService;


    public DeliverySchedularService(DeliveryAssignmentRepository repository) {
        this.repository = repository;
        this.executorService = Executors.newFixedThreadPool(5);
    }

    @Scheduled(fixedRate = 60000)
    public void simulateDeliveryUpdates(){

        System.out.println("Schedular is running");
        List<DeliveryAssignment> assignments = repository.findByPickupAtIsNullOrDeliveryAtIsNull()  ;

        for(DeliveryAssignment assignment:assignments){
            executorService.submit(()-> ProcessAssignment(assignment.getId()));
        }
    }

    private void ProcessAssignment(long assignmentId) {
        try{
            Thread.sleep(2000);

            DeliveryAssignment assignment = repository.findById(assignmentId).orElseThrow();

            PickupRequestDTO dto = new PickupRequestDTO();
            dto.setAgentId(assignment.getAgent().getUserId());
            dto.setPackageId(assignment.getPkg().getPackageId());

            if (assignment.getPickupAt() == null){
                deliveryAssignmentService.setPickup(dto);
                System.out.println("Picked : " + assignment.getId());
            }else if (assignment.getDeliveryAt() == null){
                deliveryAssignmentService.delivered(dto);
                System.out.println("delivered: " +assignment.getId());
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
