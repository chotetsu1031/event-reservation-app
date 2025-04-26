// com.example.event_reservation.controller.EventController.java
package com.example.event_reservation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.event_reservation.model.Event;
import com.example.event_reservation.model.SubEvent;
import com.example.event_reservation.repository.EventRepository;
import com.example.event_reservation.repository.SubEventRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventRepository eventRepository;
    private final SubEventRepository subEventRepository;

    @GetMapping("")
    public String showEventList(Model model) {
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping("/{id}")
    public String showEventDetail(@PathVariable Long id, Model model) {
        // idに対応するイベント情報をDBから取ってくる
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id:" + id));

        // サブイベント一覧も取得する
        List<SubEvent> subEvents = subEventRepository.findByEventId(id);

        // 画面に渡す
        model.addAttribute("event", event);
        model.addAttribute("subEvents", subEvents);
        
        return "event_detail"; // templates/event_detail.html を表示
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
    	//空のEventをテンプレートに渡す
        model.addAttribute("event", new Event());
        return "event_form"; // templates/event_form.html
    }

    @PostMapping("")
    public String createEvent(@Valid @ModelAttribute Event event, BindingResult bindingResult, Model model) {
    	if(bindingResult.hasErrors()) {
    		return "event_form";
    	}
    	//SubEventに親のEventをセット（双方向のマッピングを考慮）
    	for (SubEvent subEvent : event.getSubEvents()) {
    		subEvent.setEvent(event);
    	}
    	//Eventごと保存（CascadeType.AllでSubEventも一緒に保存される
        eventRepository.save(event);
        return "redirect:/events";
    }
}
