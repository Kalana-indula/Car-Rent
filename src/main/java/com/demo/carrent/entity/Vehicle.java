package com.demo.carrent.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="vehicle")
@Setter
@Getter
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "brand")
    private String brand;

    @Column(name = "variant")
    private String variant;

    @Column(name = "fuel")
    private String fuel;

    @Column(name="rent_per_day")
    private Double rentPerDay;

    @Column(name = "is_available")
    private Boolean isAvailable;

    //create a column for foriegn key
    @ManyToOne
    @JoinColumn(name ="vehicle_category_id")
    private VehicleCategory vehicleCategory;
}
