/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "user_season", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSeason.findAll", query = "SELECT u FROM UserSeason u")
    , @NamedQuery(name = "UserSeason.findByUserSeasonId", query = "SELECT u FROM UserSeason u WHERE u.userSeasonPK.userSeasonId = :userSeasonId")
    , @NamedQuery(name = "UserSeason.findBySeasonId", query = "SELECT u FROM UserSeason u WHERE u.userSeasonPK.seasonId = :seasonId")
    , @NamedQuery(name = "UserSeason.findBySeasonIdWithOrder", query = "SELECT u FROM UserSeason u WHERE u.userSeasonPK.seasonId = :seasonId ORDER BY u.score DESC")
    , @NamedQuery(name = "UserSeason.findBySeasonIdAndUserId", query = "SELECT u FROM UserSeason u WHERE u.userSeasonPK.userId = :userId AND u.userSeasonPK.seasonId = :seasonId")
    , @NamedQuery(name = "UserSeason.getUserSeasonScore", query = "SELECT u FROM UserSeason u WHERE u.userSeasonPK.userId = :userId and u.userSeasonPK.seasonId = (SELECT MAX(us.userSeasonPK.seasonId) from UserSeason us)") , @NamedQuery(name = "UserSeason.findByUserId", query = "SELECT u FROM UserSeason u WHERE u.userSeasonPK.userId = :userId")                                                         
    , @NamedQuery(name = "UserSeason.findByScore", query = "SELECT u FROM UserSeason u WHERE u.score = :score")
    , @NamedQuery(name = "UserSeason.findByTripCount", query = "SELECT u FROM UserSeason u WHERE u.tripCount = :tripCount")})
public class UserSeason implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
  
    protected UserSeasonPK userSeasonPK;
    @Column(name = "score")
    
    private Integer score;
    @Column(name = "trip_count")
    
    private Integer tripCount;
    @JoinColumn(name = "season_id", referencedColumnName = "season_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
  
  
    
    private Season season;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    
    private User user;

    public UserSeason() {
    }

    public UserSeason(UserSeasonPK userSeasonPK) {
        this.userSeasonPK = userSeasonPK;
    }

    public UserSeason(int userSeasonId, int seasonId, int userId) {
        this.userSeasonPK = new UserSeasonPK(userSeasonId, seasonId, userId);
    }
   
    public UserSeasonPK getUserSeasonPK() {
        return userSeasonPK;
    }

    public void setUserSeasonPK(UserSeasonPK userSeasonPK) {
        this.userSeasonPK = userSeasonPK;
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
        hash += (userSeasonPK != null ? userSeasonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSeason)) {
            return false;
        }
        UserSeason other = (UserSeason) object;
        if ((this.userSeasonPK == null && other.userSeasonPK != null) || (this.userSeasonPK != null && !this.userSeasonPK.equals(other.userSeasonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bean.UserSeason[ userSeasonPK=" + userSeasonPK + " ]";
    }
    
}
