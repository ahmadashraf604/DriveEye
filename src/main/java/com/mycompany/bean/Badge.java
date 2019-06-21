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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ashraf_R
 */
@Entity
@Table(name = "badge", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Badge.findAll", query = "SELECT b FROM Badge b")
    , @NamedQuery(name = "Badge.findByBadgeId", query = "SELECT b FROM Badge b WHERE b.badgeId = :badgeId")
    , @NamedQuery(name = "Badge.findByName", query = "SELECT b FROM Badge b WHERE b.name = :name")
    , @NamedQuery(name = "Badge.findByType", query = "SELECT b FROM Badge b WHERE b.type = :type")})
public class Badge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "badge_id")
    private Integer badgeId;
    @Lob
    @Column(name = "image")
    private byte[] image;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "badge")
    private Collection<UserSeasonBadge> userSeasonBadgeCollection;

    public Badge() {
    }

    public Badge(Integer badgeId) {
        this.badgeId = badgeId;
    }

    public Integer getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Integer badgeId) {
        this.badgeId = badgeId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        hash += (badgeId != null ? badgeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Badge)) {
            return false;
        }
        Badge other = (Badge) object;
        if ((this.badgeId == null && other.badgeId != null) || (this.badgeId != null && !this.badgeId.equals(other.badgeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bean.Badge[ badgeId=" + badgeId + " ]";
    }
    
}
