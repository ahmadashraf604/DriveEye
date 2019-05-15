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
public class ErrorReport {

    String propertyPath;
    String message;

    public ErrorReport(String propertyPath, String message) {
        this.propertyPath = propertyPath;
        this.message = message;
    }

    public String getPropertyPath() {
        return propertyPath;
    }

    public void setPropertyPath(String propertyPath) {
        this.propertyPath = propertyPath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
