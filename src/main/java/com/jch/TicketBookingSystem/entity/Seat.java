package com.jch.TicketBookingSystem.entity;
import com.jch.TicketBookingSystem.Enum.SeatStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
    @Table(name = "seats",
            uniqueConstraints = {
                    @UniqueConstraint(
                            name  = "uq_show_seat",
                            columnNames = {"show_id", "seat_number"}
                            // ↑ A1 can exist once per show — prevents duplicate seat generation
                    )
            },
            indexes = {
                    @Index(name = "idx_seats_show_id",    columnList = "show_id"),
                    @Index(name = "idx_seats_booking_id", columnList = "booking_id")
            }
    )
    @Data @Builder
@NoArgsConstructor @AllArgsConstructor
    public class Seat {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "show_id", nullable = false)
        // ↑ which show this seat belongs to
        private Show show;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "booking_id")            // nullable — empty until booked
        // ↑ CORRECTED: booking_id FK lives right here in seats table
        // ↑ No join table needed — a seat belongs to at most ONE booking
        private Booking booking;

        @Column(name = "seat_number", nullable = false)
        private String seatNumber;                  // A1, A2, B1 ...

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private SeatStatus status = SeatStatus.AVAILABLE;

        private LocalDateTime lockedAt;             // set when LOCKED, null otherwise
    }

