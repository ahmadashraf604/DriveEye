/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.dto;

public class HomeDto {
    private Integer score;
    private Long daysLeft;
    private Integer userLevel ,seasonNUmber;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(Long daysLeft) {
        this.daysLeft = daysLeft;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getSeasonNUmber() {
        return seasonNUmber;
    }

    public void setSeasonNUmber(Integer seasonNUmber) {
        this.seasonNUmber = seasonNUmber;
    }
}