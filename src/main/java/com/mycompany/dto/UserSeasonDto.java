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
public class UserSeasonDto implements Serializable {

    private int userID;
    private String username;
    private int score;
    private int rank;

    public UserSeasonDto(int userID, String username, int score, int rank) {
        this.userID = userID;
        this.username = username;
        this.score = score;
        this.rank = rank;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "com.mycompany.bean.UserSeason[  ]";
    }

}
