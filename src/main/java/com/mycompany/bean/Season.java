/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Ashraf_R
 */
@Entity
@Table(name = "season", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Season.findAll", query = "SELECT s FROM Season s")
    , @NamedQuery(name = "Season.findBySeasonId", query = "SELECT s FROM Season s WHERE s.seasonId = :seasonId")
    //, @NamedQuery(name = "Season.findByUserId", query = "SELECT s FROM Season s JOIN s.UserSeasonPK us WHERE us.userId = :userId ")
    , @NamedQuery(name = "Season.findByStartDate", query = "SELECT s FROM Season s WHERE s.startDate = :startDate")
    , @NamedQuery(name = "Season.findByEndDate", query = "SELECT s FROM Season s WHERE s.endDate = :endDate")})
public class Season implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "season_id")
    private Integer seasonId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date endDate;
    @OneToMany(mappedBy = "seasonId")

    @JsonIgnore
    private Collection<Trip> tripCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "season")
    private Collection<UserSeason> userSeasonCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "season")
    private Collection<UserSeasonBadge> userSeasonBadgeCollection;

    public Season() {
    }

    public Season(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public Season(Integer seasonId, Date startDate, Date endDate) {
        this.seasonId = seasonId;
        this.startDate = startDate;
        this.endDate = endDate;
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

    @XmlTransient
    public Collection<Trip> getTripCollection() {
        return tripCollection;
    }

    public void setTripCollection(Collection<Trip> tripCollection) {
        this.tripCollection = tripCollection;
    }

    @XmlTransient
    public Collection<UserSeason> getUserSeasonCollection() {
        return userSeasonCollection;
    }

    public void setUserSeasonCollection(Collection<UserSeason> userSeasonCollection) {
        this.userSeasonCollection = userSeasonCollection;
    }

    @XmlTransient
    public Collection<UserSeasonBadge> getUserSeasonBadgeCollection() {
        return userSeasonBadgeCollection;
    }

    public void setUserSeasonBadgeCollection(Collection<UserSeasonBadge> userSeasonBadgeCollection) {
        this.userSeasonBadgeCollection = userSeasonBadgeCollection;
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
        if (!(object instanceof Season)) {
            return false;
        }
        Season other = (Season) object;
        if ((this.seasonId == null && other.seasonId != null) || (this.seasonId != null && !this.seasonId.equals(other.seasonId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bean.Season[ seasonId=" + seasonId + " ]";
    }

}
