package com.demo.carrent.repository;

import com.demo.carrent.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<License,Long> {

    @Query(value = "SELECT * FROM license WHERE user_id = :userId",nativeQuery = true)
    License findLicenseByUser(@Param("userId")Long userId);
}
