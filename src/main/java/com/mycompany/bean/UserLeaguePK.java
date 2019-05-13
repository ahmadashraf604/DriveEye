/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ashraf_R
 */
@Embeddable
public class UserLeaguePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "user_league_id")
    private int userLeagueId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "league_id")
    private int leagueId;

    public UserLeaguePK() {
    }

    public UserLeaguePK(int userLeagueId, int userId, int leagueId) {
        this.userLeagueId = userLeagueId;
        this.userId = userId;
        this.leagueId = leagueId;
    }

    public int getUserLeagueId() {
        return userLeagueId;
    }

    public void setUserLeagueId(int userLeagueId) {
        this.userLeagueId = userLeagueId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userLeagueId;
        hash += (int) userId;
        hash += (int) leagueId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserLeaguePK)) {
            return false;
        }
        UserLeaguePK other = (UserLeaguePK) object;
        if (this.userLeagueId != other.userLeagueId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (this.leagueId != other.leagueId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bean.UserLeaguePK[ userLeagueId=" + userLeagueId + ", userId=" + userId + ", leagueId=" + leagueId + " ]";
    }
    
}
