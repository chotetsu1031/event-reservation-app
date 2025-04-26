package com.example.event_reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.event_reservation.model.SubEvent;

public interface SubEventRepository extends JpaRepository<SubEvent, Long> {
	List<SubEvent> findByEventId(Long eventId);
}
