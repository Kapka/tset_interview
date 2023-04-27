package com.example.tset.repository;

import com.example.tset.model.DeployedService;
import com.example.tset.model.SystemVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SystemVersionRepository extends JpaRepository<SystemVersion, Long> {

    public SystemVersion findTopByOrderByIdDesc();

    public SystemVersion findByVersion(int version);
}
