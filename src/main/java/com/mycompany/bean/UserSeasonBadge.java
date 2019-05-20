/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ashraf_R
 */
@Entity
@Table(name = "user_season_badge", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSeasonBadge.findAll", query = "SELECT u FROM UserSeasonBadge u")
    , @NamedQuery(name = "UserSeasonBadge.findByUserSeasonBadgeId", query = "SELECT u FROM UserSeasonBadge u WHERE u.userSeasonBadgePK.userSeasonBadgeId = :userSeasonBadgeId")
    , @NamedQuery(name = "UserSeasonBadge.findByUserIdAndSeasonId", query = "SELECT u FROM UserSeasonBadge u WHERE u.userSeasonBadgePK.userId = :userId And u.userSeasonBadgePK.seasonId = :seasonId")
    , @NamedQuery(name = "UserSeasonBadge.findByUserId", query = "SELECT u FROM UserSeasonBadge u WHERE u.userSeasonBadgePK.userId = :userId")
    , @NamedQuery(name = "UserSeasonBadge.findByBadgeId", query = "SELECT u FROM UserSeasonBadge u WHERE u.userSeasonBadgePK.badgeId = :badgeId")
    , @NamedQuery(name = "UserSeasonBadge.findBySeasonId", query = "SELECT u FROM UserSeasonBadge u WHERE u.userSeasonBadgePK.seasonId = :seasonId")})
public class UserSeasonBadge implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserSeasonBadgePK userSeasonBadgePK;
    @JoinColumn(name = "badge_id", referencedColumnName = "badge_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Badge badge;
    @JoinColumn(name = "season_id", referencedColumnName = "season_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Season season;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public UserSeasonBadge() {
    }

    public UserSeasonBadge(Badge badge, Season season, User user) {
        this.badge = badge;
        this.season = season;
        this.user = user;
        userSeasonBadgePK=new UserSeasonBadgePK( user.getUserId(), badge.getBadgeId(), season.getSeasonId());
    }

    public UserSeasonBadge(UserSeasonBadgePK userSeasonBadgePK) {
        this.userSeasonBadgePK = userSeasonBadgePK;
    }

    public UserSeasonBadge(int userSeasonBadgeId, int userId, int badgeId, int seasonId) {
        this.userSeasonBadgePK = new UserSeasonBadgePK(userSeasonBadgeId, userId, badgeId, seasonId);
    }

    public UserSeasonBadgePK getUserSeasonBadgePK() {
        return userSeasonBadgePK;
    }

    public void setUserSeasonBadgePK(UserSeasonBadgePK userSeasonBadgePK) {
        this.userSeasonBadgePK = userSeasonBadgePK;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userSeasonBadgePK != null ? userSeasonBadgePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSeasonBadge)) {
            return false;
        }
        UserSeasonBadge other = (UserSeasonBadge) object;
        if ((this.userSeasonBadgePK == null && other.userSeasonBadgePK != null) || (this.userSeasonBadgePK != null && !this.userSeasonBadgePK.equals(other.userSeasonBadgePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bean.UserSeasonBadge[ userSeasonBadgePK=" + userSeasonBadgePK + " ]";
    }
    
}
