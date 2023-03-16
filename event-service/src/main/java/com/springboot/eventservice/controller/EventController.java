package com.springboot.eventservice.controller;

import com.springboot.eventservice.model.Event;
import com.springboot.eventservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    @GetMapping("/all")
    public ResponseEntity<Iterable<Event>> getAllEvents() {
        try {
            Iterable<Event> result = eventService.getEvents();
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping
    public ResponseEntity<String> addEvent(@RequestBody Event event) {
        try {
            eventService.createEvent(event);
            return new ResponseEntity<>("New event added", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add new event" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
