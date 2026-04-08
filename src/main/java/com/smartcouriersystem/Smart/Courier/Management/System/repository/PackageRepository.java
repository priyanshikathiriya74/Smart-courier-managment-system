package com.smartcouriersystem.Smart.Courier.Management.System.repository;

import com.smartcouriersystem.Smart.Courier.Management.System.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package,Long> {
}
