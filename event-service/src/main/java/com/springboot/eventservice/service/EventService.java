package com.springboot.eventservice.service;

import com.springboot.eventservice.repository.EventRepository;
import com.springboot.eventservice.model.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {
    private final EventRepository eventRepository;

    public void createEvent(Event event) {
        eventRepository.save(event);
        log.info("new event {} is saved", event.getId());
    }

    public Iterable<Event> getEvents() {
        Iterable<Event> allEvents = eventRepository.findAll();
        return allEvents;
    }
}
