package com.example.diplom.service;

import com.example.diplom.dto.request.EventRequest;
import com.example.diplom.dto.response.EventResponse;
import com.example.diplom.entity.Event;
import com.example.diplom.exception.NotFoundException;
import com.example.diplom.exception.NotUniqueException;
import com.example.diplom.mapper.event.EventMapper;
import com.example.diplom.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventResponse createEvent(EventRequest eventRequest) {
        log.info("Start creating event with name = {}", eventRequest.getName());
        validateEventByName(eventRequest.getName());

        Event eventToSave = eventMapper.toEvent(eventRequest);
        Event savedEvent = eventRepository.save(eventToSave);

        log.info("End creating event with name = {} and id = {}", savedEvent.getName(), eventToSave.getId());

        return eventMapper.toEventResponse(savedEvent);
    }

    public EventResponse getEvent(UUID id) {
        log.info("Start getting event with id = {}", id);
        Event loadedEvent = loadEvent(id);
        return eventMapper.toEventResponse(loadedEvent);
    }

    public EventResponse updateEvent(UUID id, EventRequest eventRequest) {
        log.info("Start updating event with id = {} and body = {}", id, eventRequest);

        Event loadedEvent = loadEvent(id);
        validateEventByName(eventRequest.getName());
        Event updatedEventToSave = eventMapper.updateEvent(loadedEvent, eventRequest);
        Event updatedEvent = eventRepository.save(updatedEventToSave);

        log.info("End updating event with name = {} and id = {}", eventRequest.getName(), updatedEvent.getId());

        return eventMapper.toEventResponse(updatedEvent);
    }

    public List<EventResponse> getEvents() {
        log.info("Start getting events");
        List<Event> loadedEvents = eventRepository.findAll();

        return loadedEvents.stream()
                .map(eventMapper::toEventResponse)
                .toList();
    }

    public void deleteEvent(UUID id) {
        log.info("Start deleting event with id = {}", id);

        validateEventById(id);
        eventRepository.deleteById(id);
    }

    private Event loadEvent(UUID id) {
        return eventRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Event with id = " + id + " not found")
                );
    }

    private void validateEventByName(String name) {
        if (eventRepository.existsByName(name)) {
            throw new NotUniqueException("Event with name " + name + " already exists");
        }
    }

    private void validateEventById(UUID id) {
        if (!eventRepository.existsById(id)) {
            throw new NotFoundException("Event with id " + id + " not found");
        }
    }
}
