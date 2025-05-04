package com.example.event_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.event_reservation.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
