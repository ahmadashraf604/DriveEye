/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ashraf_R
 */
public class CarDto implements Serializable {

    private Integer carId;
    private String brand;
    private String model;
    private byte[] image;
    private Date releaseyear;

    public CarDto() {
    }

    public CarDto(Integer carId) {
        this.carId = carId;
    }

    public CarDto(Integer carId, String brand,String model ) {
        this.carId = carId;
        this.brand = brand;
        this.model=model;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(Date releaseyear) {
        this.releaseyear = releaseyear;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carId != null ? carId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarDto)) {
            return false;
        }
        CarDto other = (CarDto) object;
        if ((this.carId == null && other.carId != null) || (this.carId != null && !this.carId.equals(other.carId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dto.Car[ carId=" + carId + " ]";
    }
    
}
