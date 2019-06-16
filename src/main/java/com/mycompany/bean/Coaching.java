/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ashraf_R
 */
@Entity
@Table(name = "coaching", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coaching.findAll", query = "SELECT c FROM Coaching c")
    , @NamedQuery(name = "Coaching.findByCoachingId", query = "SELECT c FROM Coaching c WHERE c.coachingId = :coachingId")
    , @NamedQuery(name = "Coaching.findByTitle", query = "SELECT c FROM Coaching c WHERE c.title = :title")
    , @NamedQuery(name = "Coaching.findByDescription", query = "SELECT c FROM Coaching c WHERE c.description = :description")})
public class Coaching implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coaching_id")
    private Integer coachingId;
    @Size(max = 45)
    @Column(name = "title")
    private String title;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Lob
    @Column(name = "image")
    private byte[] image;

    public Coaching() {
    }

    public Coaching(Integer coachingId) {
        this.coachingId = coachingId;
    }

    public Integer getCoachingId() {
        return coachingId;
    }

    public void setCoachingId(Integer coachingId) {
        this.coachingId = coachingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coachingId != null ? coachingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coaching)) {
            return false;
        }
        Coaching other = (Coaching) object;
        if ((this.coachingId == null && other.coachingId != null) || (this.coachingId != null && !this.coachingId.equals(other.coachingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bean.Coaching[ coachingId=" + coachingId + " ]";
    }
    
}
