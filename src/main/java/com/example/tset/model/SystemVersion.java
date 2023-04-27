package com.example.tset.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class SystemVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int version;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "SERVICE_VERSION_DEPLOY_SERVICE",
            joinColumns = @JoinColumn(name = "systemVersion_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "deployedService_id", referencedColumnName = "id"))
    private Set<DeployedService> deployedServiceSet = new HashSet<>();

    public SystemVersion() {

    }

    public SystemVersion(int version) {
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Set<DeployedService> getDeployedServiceSet() {
        return deployedServiceSet;
    }

    public void setDeployedServiceSet(Set<DeployedService> deployedServiceSet) {
        this.deployedServiceSet = deployedServiceSet;
    }

    public void addDeployedService(DeployedService deployedService) {
        this.deployedServiceSet.add(deployedService);
    }
}
