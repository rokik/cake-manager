package com.waracle.cakemanager.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class CakeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long cakeId;

    @Column(name = "TITLE", unique = true, nullable = false, length = 100)
    private String title;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "IMAGE_URL", length = 500)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private EmployeeEntity employee;

    public Long getCakeId() {
        return cakeId;
    }

    public void setCakeId(Long cakeId) {
        this.cakeId = cakeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }
}