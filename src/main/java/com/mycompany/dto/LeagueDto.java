/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dto;

import java.io.Serializable;

/**
 *
 * @author Ashraf_R
 */
public class LeagueDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer leagueId;
    private String name;
    private String code;
    private Integer userCount;
    private UserDto ownerId;

    public LeagueDto() {
    }

    public LeagueDto(Integer leagueId) {
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

    public UserDto getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UserDto ownerId) {
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
        if (!(object instanceof LeagueDto)) {
            return false;
        }
        LeagueDto other = (LeagueDto) object;
        if ((this.leagueId == null && other.leagueId != null) || (this.leagueId != null && !this.leagueId.equals(other.leagueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dto.League[ leagueId=" + leagueId + " ]";
    }

}
