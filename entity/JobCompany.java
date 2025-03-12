package com.luv2code.jobportal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class JobCompany {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String logo;
    private String name;

    public JobCompany() {
    }

    public JobCompany(Integer id, String logo, String name) {
        Id = id;
        this.logo = logo;
        this.name = name;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JobCompany{" +
                "Id=" + Id +
                ", logo='" + logo + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
