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
@Table(name = "user_league", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserLeague.findAll", query = "SELECT u FROM UserLeague u")
    , @NamedQuery(name = "UserLeague.findByUserLeagueId", query = "SELECT u FROM UserLeague u WHERE u.userLeaguePK.userLeagueId = :userLeagueId order by u.score DESC")
    , @NamedQuery(name = "UserLeague.findByUserIdAndLeagueId", query = "SELECT u FROM UserLeague u WHERE u.userLeaguePK.userId = :userId AND u.userLeaguePK.leagueId = :leagueId")
    , @NamedQuery(name = "UserLeague.findByUserId", query = "SELECT u FROM UserLeague u WHERE u.userLeaguePK.userId = :userId order by u.score DESC")
    , @NamedQuery(name = "UserLeague.findByLeagueId", query = "SELECT u FROM UserLeague u WHERE u.userLeaguePK.leagueId = :leagueId")
    , @NamedQuery(name = "UserLeague.findByScore", query = "SELECT u FROM UserLeague u WHERE u.score = :score")})
public class UserLeague implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserLeaguePK userLeaguePK;
    @Column(name = "score")
    private Integer score;
    @JoinColumn(name = "league_id", referencedColumnName = "league_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private League league;
    
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public UserLeague() {
    }

    public UserLeague(UserLeaguePK userLeaguePK) {
        this.userLeaguePK = userLeaguePK;
    }

    public UserLeague(int userLeagueId, int userId, int leagueId) {
        this.userLeaguePK = new UserLeaguePK(userLeagueId, userId, leagueId);
    }

    public UserLeaguePK getUserLeaguePK() {
        return userLeaguePK;
    }

    public void setUserLeaguePK(UserLeaguePK userLeaguePK) {
        this.userLeaguePK = userLeaguePK;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
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
        hash += (userLeaguePK != null ? userLeaguePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserLeague)) {
            return false;
        }
        UserLeague other = (UserLeague) object;
        if ((this.userLeaguePK == null && other.userLeaguePK != null) || (this.userLeaguePK != null && !this.userLeaguePK.equals(other.userLeaguePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bean.UserLeague[ userLeaguePK=" + userLeaguePK + " ]";
    }
    
}
