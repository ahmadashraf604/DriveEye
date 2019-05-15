/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utill;

/**
 *
 * @author Ashraf_R
 */
public class Response<T> {

    private boolean status;
    private T response;

    public Response(boolean status, T response) {
        this.status = status;
        this.response = response;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

}
