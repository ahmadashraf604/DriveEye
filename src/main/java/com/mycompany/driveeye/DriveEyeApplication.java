/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.driveeye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Sallam
 */
@SpringBootApplication
@EntityScan("com.mycompany.bean")
@EnableJpaRepositories("com.mycompany.dao")
@ComponentScan("com.mycompany.controller")
public class DriveEyeApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DriveEyeApplication.class, args);
    }
}
