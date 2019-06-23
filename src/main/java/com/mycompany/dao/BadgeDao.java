/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.bean.Badge;
import com.mycompany.bean.User;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ashraf_R
 */
@Repository
public interface BadgeDao extends CrudRepository<Badge, Integer> {
 
    @Transactional
    @Modifying
    @Query(value = "update Badge b set b.image = :image where b.id = :badgeId")
    public void updateImage(@Param("image") byte[] image, @Param("badgeId") Integer badgeId);
    
}