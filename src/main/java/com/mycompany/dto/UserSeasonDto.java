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
public class UserSeasonDto implements Serializable {

     private Integer score;
     private Integer tripCount;
    private SeasonDto season;

    public UserSeasonDto() {
    }

  
   
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTripCount() {
        return tripCount;
    }

    public void setTripCount(Integer tripCount) {
        this.tripCount = tripCount;
    }

    public SeasonDto getSeason() {
        return season;
    }

    public void setSeason(SeasonDto season) {
        this.season = season;
    }

   
    @Override
    public String toString() {
        return "com.mycompany.bean.UserSeason[  ]";
    }
    
}
