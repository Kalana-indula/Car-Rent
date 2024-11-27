package com.demo.carrent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "vehicle_category")
@Setter
@Getter
public class VehicleCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    //to avoid circular dependency error
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "vehicleCategory",cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;
}
