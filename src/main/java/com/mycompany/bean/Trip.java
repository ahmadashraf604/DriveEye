/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ashraf_R
 */
@Entity
@Table(name = "trip", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trip.findAll", query = "SELECT t FROM Trip t")
    , @NamedQuery(name = "Trip.findByTripId", query = "SELECT t FROM Trip t WHERE t.tripId = :tripId")
    , @NamedQuery(name = "Trip.findByUserId", query = "SELECT t FROM Trip t WHERE t.userId = :userId")
    , @NamedQuery(name = "Trip.findByStartPoint", query = "SELECT t FROM Trip t WHERE t.startPoint = :startPoint")
    , @NamedQuery(name = "Trip.findByEndPoint", query = "SELECT t FROM Trip t WHERE t.endPoint = :endPoint")
    , @NamedQuery(name = "Trip.findByDuration", query = "SELECT t FROM Trip t WHERE t.duration = :duration")
    , @NamedQuery(name = "Trip.findByDescription", query = "SELECT t FROM Trip t WHERE t.description = :description")})
public class Trip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "trip_id")
    private Integer tripId;
    @NotNull
    @Size(max = 45)
    @Column(name = "start_point")
    private String startPoint;
    @NotNull
    @Size(max = 45)
    @Column(name = "end_point")
    private String endPoint;
    @NotNull
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "duration")
    private Double duration;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "season_id", referencedColumnName = "season_id")
    @ManyToOne
    private Season seasonId;
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private User userId;

    public Trip() {
    }

    public Trip(Integer tripId) {
        this.tripId = tripId;
    }

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Season getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Season seasonId) {
        this.seasonId = seasonId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tripId != null ? tripId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trip)) {
            return false;
        }
        Trip other = (Trip) object;
        if ((this.tripId == null && other.tripId != null) || (this.tripId != null && !this.tripId.equals(other.tripId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bean.Trip[ tripId=" + tripId + " ]";
    }
    
}
