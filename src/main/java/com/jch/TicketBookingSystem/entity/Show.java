package com.jch.TicketBookingSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
    @Table(name = "shows", indexes = {
            @Index(name = "idx_shows_event_id", columnList = "event_id")
    })
    @Data
@Builder @NoArgsConstructor @AllArgsConstructor
    public class Show {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "event_id", nullable = false)
        // ↑ creates event_id column in shows table
        // ↑ many shows belong to one event
        private Event event;

        @Column(nullable = false)
        private LocalDateTime showTime;

        @Column(nullable = false)
        private String venue;

        @Column(nullable = false)
        private Integer totalSeats;
    }

