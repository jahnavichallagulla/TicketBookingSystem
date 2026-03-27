package com.jch.TicketBookingSystem.entity;

import jakarta.persistence.*;
import lombok.*;


    @Entity
    @Table(name = "events", indexes = {
            @Index(name = "idx_events_organizer_id", columnList = "organizer_id")
    })
    @Data @Builder
    @NoArgsConstructor @AllArgsConstructor
    public class Event {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String title;

        private String description;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "organizer_id", nullable = false)
        private User organizer;
    }

