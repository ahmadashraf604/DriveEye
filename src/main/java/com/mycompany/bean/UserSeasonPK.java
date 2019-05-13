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
public class UserSeasonPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "user_season_id")
    private int userSeasonId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "season_id")
    private int seasonId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;

    public UserSeasonPK() {
    }

    public UserSeasonPK(int userSeasonId, int seasonId, int userId) {
        this.userSeasonId = userSeasonId;
        this.seasonId = seasonId;
        this.userId = userId;
    }

    public int getUserSeasonId() {
        return userSeasonId;
    }

    public void setUserSeasonId(int userSeasonId) {
        this.userSeasonId = userSeasonId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userSeasonId;
        hash += (int) seasonId;
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSeasonPK)) {
            return false;
        }
        UserSeasonPK other = (UserSeasonPK) object;
        if (this.userSeasonId != other.userSeasonId) {
            return false;
        }
        if (this.seasonId != other.seasonId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bean.UserSeasonPK[ userSeasonId=" + userSeasonId + ", seasonId=" + seasonId + ", userId=" + userId + " ]";
    }
    
}
