package com.jch.TicketBookingSystem.entity;

import com.jch.TicketBookingSystem.Enum.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_users_email", columnList = "email") // Phase 1 DB indexing requirement
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)  // email must be unique — DB constraint
    private String email;

    @Column(nullable = false)
    private String password;   // will store BCrypt hash, never plain text

    @Enumerated(EnumType.STRING)  // stores "USER" or "ORGANIZER" as text, not 0/1
    @Column(nullable = false)
    private Role role;
}
