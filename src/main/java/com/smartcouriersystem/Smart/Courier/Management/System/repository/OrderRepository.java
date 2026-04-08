package com.smartcouriersystem.Smart.Courier.Management.System.repository;

import com.smartcouriersystem.Smart.Courier.Management.System.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query(value = "select * from orders where customer_id = :customerId",nativeQuery = true)
    Optional<List<Order>> findByCustomerId(@Param("customerId")long customerId);
}
