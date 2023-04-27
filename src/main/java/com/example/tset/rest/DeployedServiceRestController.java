package com.example.tset.rest;

import com.example.tset.model.DeployedService;
import com.example.tset.repository.DeployedServiceRepository;
import com.example.tset.service.DeployServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class DeployedServiceRestController {

    @Autowired
    private DeployServiceInterface deployService;

    @PostMapping("/deploy")
    public Integer deploy(@RequestBody DeployedService deployedService)
    {
        return deployService.deploy(deployedService);
    }

    @GetMapping("/services")
    public Set<DeployedService> getDeployedServices(@RequestParam(value = "systemVersion") int systemVersion)
    {
        return deployService.getDeployedServices(systemVersion);
    }
}
