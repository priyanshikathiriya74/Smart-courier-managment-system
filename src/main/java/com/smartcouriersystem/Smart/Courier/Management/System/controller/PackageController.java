package com.smartcouriersystem.Smart.Courier.Management.System.controller;

import com.smartcouriersystem.Smart.Courier.Management.System.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("package")
public class PackageController {
    @Autowired
    PackageService packageService;
}
