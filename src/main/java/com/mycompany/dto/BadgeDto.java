/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dto;

import java.io.Serializable;

/**
 *
 * @author Ashraf_R
 */
public class BadgeDto implements Serializable {

    private Integer badgeId;
    private String image;
    private String name;
    private String type;

    public BadgeDto(Integer badgeId, String image, String name, String type) {
        this.badgeId = badgeId;
        this.image = image;
        this.name = name;
        this.type = type;
    }
 

    public BadgeDto() {
    }

    public BadgeDto(Integer badgeId) {
        this.badgeId = badgeId;
    }

    public BadgeDto(Integer badgeId, String name, String type) {
         this.badgeId = badgeId;
        this.name = name;
        this.type = type;
    }

    public Integer getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Integer badgeId) {
        this.badgeId = badgeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (badgeId != null ? badgeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BadgeDto)) {
            return false;
        }
        BadgeDto other = (BadgeDto) object;
        if ((this.badgeId == null && other.badgeId != null) || (this.badgeId != null && !this.badgeId.equals(other.badgeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bean.Badge[ badgeId=" + badgeId + " ]";
    }
    
}
