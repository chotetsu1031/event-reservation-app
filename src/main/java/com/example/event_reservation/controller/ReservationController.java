// com.example.event_reservation.controller.ReservationController.java

package com.example.event_reservation.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.event_reservation.model.Event;
import com.example.event_reservation.model.Reservation;
import com.example.event_reservation.model.User;
import com.example.event_reservation.repository.EventRepository;
import com.example.event_reservation.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final EventRepository eventRepository;
    private final ReservationRepository reservationRepository;

    // 一般ユーザー向けイベント一覧表示
    @GetMapping("/events")
    public String showEventListForReservation(Model model) {
        List<Event> events = eventRepository.findAll(); // 開催日順に並べてもいいかも
        model.addAttribute("events", events);
        return "reservation_event_list"; // templates/reservation_event_list.html を表示
    }
    @GetMapping("/events/{id}")
    public String showEventDetailForReservation(@PathVariable("id") Long eventId, Model model) {
        Event event = eventRepository.findById(eventId)
                                     .orElseThrow(() -> new IllegalArgumentException("イベントが存在しません"));
        model.addAttribute("event", event);
        model.addAttribute("reservation", new Reservation());
        return "reservation_event_detail"; // templates/reservation_event_detail.html を作成する
    }

    // 一覧・予約
    @PostMapping("/confirm")
    public String createReservation(@RequestParam("eventId") Long eventId,  @AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
    	Event event = eventRepository.findById(eventId).orElseThrow();
    	Reservation reservation = new Reservation();
    	
    	reservation.setLoginUser(user);
    	reservation.setEvent(event);
    	//予約情報を保存
    	reservationRepository.save(reservation);
    	redirectAttributes.addFlashAttribute("message", "予約が完了しました。");
    	return "redirect:/reservations/events";
    }
}
