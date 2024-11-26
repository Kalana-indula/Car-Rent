package com.demo.carrent.repository;

import com.demo.carrent.entity.VehicleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleCategoryRepository extends JpaRepository<VehicleCategory,Long> {

}
