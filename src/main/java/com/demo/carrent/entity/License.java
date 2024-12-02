package com.demo.carrent.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "license")
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "image_path")
    private String imagePath;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
