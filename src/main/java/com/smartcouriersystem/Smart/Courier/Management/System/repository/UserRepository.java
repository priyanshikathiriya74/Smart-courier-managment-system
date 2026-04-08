package com.smartcouriersystem.Smart.Courier.Management.System.repository;

import com.smartcouriersystem.Smart.Courier.Management.System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select * from user where role = 'AGENT'",nativeQuery = true)
    List<User> findAllAgent();
}
