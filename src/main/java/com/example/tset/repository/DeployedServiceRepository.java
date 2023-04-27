package com.example.tset.repository;

import com.example.tset.model.DeployedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeployedServiceRepository extends JpaRepository<DeployedService, Long> {

    public DeployedService findByNameAndVersion(String name, int version);
}
