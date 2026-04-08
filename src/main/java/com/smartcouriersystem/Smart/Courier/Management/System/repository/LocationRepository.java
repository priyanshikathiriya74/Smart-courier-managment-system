package com.smartcouriersystem.Smart.Courier.Management.System.repository;

import com.smartcouriersystem.Smart.Courier.Management.System.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
}
