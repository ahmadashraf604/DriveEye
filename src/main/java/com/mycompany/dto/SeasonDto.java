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
public class SeasonDto implements Serializable {

    private Integer seasonId;
    private Date startDate;
    private Date endDate;
    private Integer highScore;
    private Integer myScore;
    private Integer myRank;

    public SeasonDto(Integer seasonId, Date startDate, Date endDate, Integer highScore, Integer myScore, Integer myRank) {
        this.seasonId = seasonId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.highScore = highScore;
        this.myScore = myScore;
        this.myRank = myRank;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getHighScore() {
        return highScore;
    }

    public void setHighScore(Integer highScore) {
        this.highScore = highScore;
    }

    public Integer getMyScore() {
        return myScore;
    }

    public void setMyScore(Integer myScore) {
        this.myScore = myScore;
    }

    public Integer getMyRank() {
        return myRank;
    }

    public void setMyRank(Integer myRank) {
        this.myRank = myRank;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seasonId != null ? seasonId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeasonDto)) {
            return false;
        }
        SeasonDto other = (SeasonDto) object;
        if ((this.seasonId == null && other.seasonId != null) || (this.seasonId != null && !this.seasonId.equals(other.seasonId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dto.Season[ seasonId=" + seasonId + " ]";
    }

}
