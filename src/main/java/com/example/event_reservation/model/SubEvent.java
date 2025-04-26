package com.example.event_reservation.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class SubEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "開始時間は必須です。")
    private LocalDateTime startTime;

    @NotNull(message = "終了時間は必須です。")
    private LocalDateTime endTime;

    private String location;

    private String activity;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
