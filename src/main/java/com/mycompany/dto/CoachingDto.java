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
public class CoachingDto implements Serializable {

    private static final long serialVersionUID = 1L;
   private Integer coachingId;
    private String title;
    private String description;
    private byte[] image;

    public CoachingDto() {
    }

    public CoachingDto(Integer coachingId) {
        this.coachingId = coachingId;
    }

    public Integer getCoachingId() {
        return coachingId;
    }

    public void setCoachingId(Integer coachingId) {
        this.coachingId = coachingId;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coachingId != null ? coachingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoachingDto)) {
            return false;
        }
        CoachingDto other = (CoachingDto) object;
        if ((this.coachingId == null && other.coachingId != null) || (this.coachingId != null && !this.coachingId.equals(other.coachingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dto.Coaching[ coachingId=" + coachingId + " ]";
    }
    
}
