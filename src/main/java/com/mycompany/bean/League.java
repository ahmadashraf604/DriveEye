/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.context.annotation.Lazy;

/**
 *
 * @author Ashraf_R
 */
@Entity
@Table(name = "league", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "League.findAll", query = "SELECT l FROM League l")
    , @NamedQuery(name = "League.findByLeagueId", query = "SELECT l FROM League l WHERE l.leagueId = :leagueId")
    , @NamedQuery(name = "League.findByName", query = "SELECT l FROM League l WHERE l.name = :name")
    , @NamedQuery(name = "League.findByCode", query = "SELECT l FROM League l WHERE l.code = :code")
    , @NamedQuery(name = "League.findByUserCount", query = "SELECT l FROM League l WHERE l.userCount = :userCount")})
public class League implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "league_id")
    private Integer leagueId;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "code")
    private String code;
    @Column(name = "user_count")
    private Integer userCount;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "league")
    private transient Collection<UserLeague> userLeagueCollection;
    @JsonIgnore
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User ownerId;

    public League() {
    }

    public League(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    @XmlTransient
    public Collection<UserLeague> getUserLeagueCollection() {
        return userLeagueCollection;
    }

    public void setUserLeagueCollection(Collection<UserLeague> userLeagueCollection) {
        this.userLeagueCollection = userLeagueCollection;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (leagueId != null ? leagueId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof League)) {
            return false;
        }
        League other = (League) object;
        if ((this.leagueId == null && other.leagueId != null) || (this.leagueId != null && !this.leagueId.equals(other.leagueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bean.League[ leagueId=" + leagueId + " ]";
    }

}
