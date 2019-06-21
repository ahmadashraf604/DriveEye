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
public class UserSeasonBadgeDto implements Serializable {

    private BadgeDto badge;
    private SeasonDto season;

    public UserSeasonBadgeDto() {
    }

    public BadgeDto getBadge() {
        return badge;
    }

    public UserSeasonBadgeDto(BadgeDto badge) {
        this.badge = badge;
    }
    

    public void setBadge(BadgeDto badge) {
        this.badge = badge;
    }

    public SeasonDto getSeason() {
        return season;
    }

    public void setSeason(SeasonDto season) {
        this.season = season;
    }  

    @Override
    public String toString() {
        return "com.mycompany.dto.UserSeasonBadge[  ]";
    }
    
}
