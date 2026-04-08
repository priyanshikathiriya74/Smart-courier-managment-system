package com.smartcouriersystem.Smart.Courier.Management.System.repository;

import com.smartcouriersystem.Smart.Courier.Management.System.model.DeliveryAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeliveryAssignmentRepository extends JpaRepository<DeliveryAssignment,Long> {

    @Query(value =
            "select * from delivery_assignment where agent_id = :agentId and package_id = :packageId",nativeQuery = true)
    Optional<DeliveryAssignment> findByAgentIdAndPickupId(@Param("agentId")long agentId,@Param("packageId") long packageId);




    List<DeliveryAssignment> findByPickupAtIsNullOrDeliveryAtIsNull();
}
