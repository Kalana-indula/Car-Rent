package com.demo.carrent.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="id_no")
    private String idNo;

    @Column(name="phone")
    private String phone;

    @Column(name = "email")
    private String email;
}
