package com.example.tset.service;

import com.example.tset.model.DeployedService;

import java.util.Set;

public interface DeployServiceInterface {

    Integer deploy(DeployedService deployedService);

    Set<DeployedService> getDeployedServices(int systemVersion);
}
