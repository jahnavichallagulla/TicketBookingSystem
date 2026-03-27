package com.jch.TicketBookingSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@Entity
    @Table(name = "bookings", indexes = {
            @Index(name = "idx_bookings_user_id", columnList = "user_id"),
            @Index(name = "idx_bookings_show_id", columnList = "show_id")
    })
    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public class Booking {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        // ↑ which customer made this booking
        private User user;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "show_id", nullable = false)
        // ↑ which show this booking is for
        private Show show;

        @OneToMany(mappedBy = "booking")
        // ↑ CORRECTED: one booking owns many seats
        // ↑ mappedBy = "booking" means the FK (booking_id) lives on the Seat side
        // ↑ NO @JoinTable — no extra table created at all
        private List<Seat> seats = new ArrayList<>();

        @Column(nullable = false)
        private String status;                      // CONFIRMED

        @Column(unique = true)
        private String idempotencyKey;              // prevents duplicate booking
    }
