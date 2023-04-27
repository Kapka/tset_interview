package com.example.tset.service;

import com.example.tset.model.DeployedService;
import com.example.tset.model.SystemVersion;
import com.example.tset.repository.DeployedServiceRepository;
import com.example.tset.repository.SystemVersionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DeployServiceImpl implements DeployServiceInterface {

    @Autowired
    private DeployedServiceRepository deployedServiceRepository;

    @Autowired
    private SystemVersionRepository systemVersionRepository;

    @Transactional
    public Integer deploy(DeployedService deployedService)
    {
        DeployedService oldDeployedService = deployedServiceRepository
                .findByNameAndVersion(deployedService.getName(), deployedService.getVersion());

        if (oldDeployedService != null)
        {
            return oldDeployedService.getLatestSystemVersion();
        }

        SystemVersion lastSystemVersion = systemVersionRepository.findTopByOrderByIdDesc();
        Integer lastSystemVersionNumber = lastSystemVersion == null ? 0 : lastSystemVersion.getVersion();

        Integer newSystemVersionNumber = lastSystemVersionNumber + 1;
        SystemVersion newSystemVersion = new SystemVersion(newSystemVersionNumber);

        Set<DeployedService> deployedServicesSet = setupDeployedServicesSet(lastSystemVersion, deployedService);

        deployedServiceRepository.save(deployedService);

        newSystemVersion.setDeployedServiceSet(deployedServicesSet);
        systemVersionRepository.save(newSystemVersion);

        return newSystemVersionNumber;
    }

    @Transactional
    @Override
    public Set<DeployedService> getDeployedServices(int version) {

        SystemVersion systemVersion = systemVersionRepository.findByVersion(version);
        return systemVersion == null
                ? new HashSet<>()
                : systemVersion.getDeployedServiceSet();
    }

    private Set<DeployedService> setupDeployedServicesSet(SystemVersion lastSystemVersion, DeployedService deployedService)
    {
        Set<DeployedService> result = new HashSet<>();
        result.add(deployedService);

        if (lastSystemVersion == null)
        {
            return result;
        }

        for (DeployedService oldService : lastSystemVersion.getDeployedServiceSet())
        {
            if (!oldService.getName().equals(deployedService.getName()))
            {
                result.add(oldService);
            }
        }

        return result;
    }
}
