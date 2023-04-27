package com.example.tset.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class DeployedService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int version;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "SERVICE_VERSION_DEPLOY_SERVICE",
            joinColumns = @JoinColumn(name = "deployedService_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "systemVersion_id", referencedColumnName = "id"))
    private Set<SystemVersion> systemVersionSet = new HashSet<>();

    public DeployedService() {

    }

    public DeployedService(String name, int version) {
        this.name = name;
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Set<SystemVersion> getSystemVersionSet() {
        return systemVersionSet;
    }

    public void setSystemVersionSet(Set<SystemVersion> systemVersionSet) {
        this.systemVersionSet = systemVersionSet;
    }

    public void addSystemVersion(SystemVersion systemVersion) {
        this.systemVersionSet.add(systemVersion);
    }

    @JsonIgnore
    public Integer getLatestSystemVersion()
    {
        return systemVersionSet.stream().mapToInt(sv -> sv.getVersion()).max().getAsInt();
    }
}
