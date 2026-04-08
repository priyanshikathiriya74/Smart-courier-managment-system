package com.smartcouriersystem.Smart.Courier.Management.System.service;

import com.smartcouriersystem.Smart.Courier.Management.System.model.Package;
import com.smartcouriersystem.Smart.Courier.Management.System.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageService {
    @Autowired
    PackageRepository packageRepository;
}
