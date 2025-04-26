// com.example.event_reservation.validation.EventTimeValidator.java
package com.example.event_reservation.validation;

import com.example.event_reservation.model.Event;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EventTimeValidator implements ConstraintValidator<ValidEventTime, Event> {

    @Override
    public boolean isValid(Event event, ConstraintValidatorContext context) {
    	//NullPointerExceptionが発生するのを防ぐ
        if (event.getStartTime() == null || event.getEndTime() == null) {
            return true; // 他の @NotNull に任せる【責任の分離】
        }

        return event.getStartTime().isBefore(event.getEndTime());
    }
}
