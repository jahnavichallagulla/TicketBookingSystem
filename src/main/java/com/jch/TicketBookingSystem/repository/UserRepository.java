package com.jch.TicketBookingSystem.repository;

import com.jch.TicketBookingSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}