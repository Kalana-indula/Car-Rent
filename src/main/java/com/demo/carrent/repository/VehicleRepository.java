package com.demo.carrent.repository;

import com.demo.carrent.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    @Query(value="SELECT*FROM vehicle WHERE vehicle_category_id = :categoryId",nativeQuery = true)
    List<Vehicle> findVehicleByCategory(@Param("categoryId")Long categoryId);
}
