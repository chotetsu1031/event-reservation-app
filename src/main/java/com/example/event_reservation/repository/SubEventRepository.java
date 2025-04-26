package com.example.event_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.event_reservation.model.SubEvent;

public interface SubEventRepository extends JpaRepository<SubEvent, Long> {
}
