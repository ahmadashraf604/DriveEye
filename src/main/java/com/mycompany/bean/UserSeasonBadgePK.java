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
public class UserSeasonBadgePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "user_season_badge_id")
    private int userSeasonBadgeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "badge_id")
    private int badgeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "season_id")
    private int seasonId;

    public UserSeasonBadgePK() {
    }

    public UserSeasonBadgePK(int userSeasonBadgeId, int userId, int badgeId, int seasonId) {
        this.userSeasonBadgeId = userSeasonBadgeId;
        this.userId = userId;
        this.badgeId = badgeId;
        this.seasonId = seasonId;
    }

    public UserSeasonBadgePK(int userId, int badgeId, int seasonId) {
        this.userId = userId;
        this.badgeId = badgeId;
        this.seasonId = seasonId;
    }

    public int getUserSeasonBadgeId() {
        return userSeasonBadgeId;
    }

    public void setUserSeasonBadgeId(int userSeasonBadgeId) {
        this.userSeasonBadgeId = userSeasonBadgeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userSeasonBadgeId;
        hash += (int) userId;
        hash += (int) badgeId;
        hash += (int) seasonId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSeasonBadgePK)) {
            return false;
        }
        UserSeasonBadgePK other = (UserSeasonBadgePK) object;
        if (this.userSeasonBadgeId != other.userSeasonBadgeId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (this.badgeId != other.badgeId) {
            return false;
        }
        if (this.seasonId != other.seasonId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bean.UserSeasonBadgePK[ userSeasonBadgeId=" + userSeasonBadgeId + ", userId=" + userId + ", badgeId=" + badgeId + ", seasonId=" + seasonId + " ]";
    }
    
}
