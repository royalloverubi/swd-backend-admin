package com.example.demo.persistent.entity;

import javax.persistence.*;

@Entity
@Table(name = "cyber_gaming")
public class CyberGaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "logo")
    private String logo;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "address")
    private String address;

    @Column(name = "number_of_star")
    private Double numberOfStar;

    @Column(name = "number_of_evaluator")
    private Integer numberOfEvaluator;

    @Column(name = "longitude")
    private Double logitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "is_active")
    private Boolean active;

    @Column(name = "is_deleted")
    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getNumberOfStar() {
        return numberOfStar;
    }

    public void setNumberOfStar(Double numberOfStar) {
        this.numberOfStar = numberOfStar;
    }

    public Integer getNumberOfEvaluator() {
        return numberOfEvaluator;
    }

    public void setNumberOfEvaluator(Integer numberOfEvaluator) {
        this.numberOfEvaluator = numberOfEvaluator;
    }

    public Double getLogitude() {
        return logitude;
    }

    public void setLogitude(Double logitude) {
        this.logitude = logitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}

