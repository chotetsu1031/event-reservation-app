// com.example.event_reservation.repository.EventRepository.java
package com.example.event_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.event_reservation.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
