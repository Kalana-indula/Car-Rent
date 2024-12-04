package com.demo.carrent.entity;

import com.demo.carrent.common.PaymentStatus;
import com.demo.carrent.common.RentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "rent")
@Setter
@Getter
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_days")
    private Integer totalDays;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "booking_time")
    private LocalDateTime bookingTime;

    @Column(name = "starting_date")
    private LocalDate startingDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "rent_status")
    private RentStatus rentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    //Create column for foreign key
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @JsonIgnore
    @OneToOne(mappedBy ="rent",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Payment payment;
}
