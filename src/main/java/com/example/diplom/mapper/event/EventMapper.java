package com.example.diplom.mapper.event;

import com.example.diplom.dto.request.EventRequest;
import com.example.diplom.dto.response.EventResponse;
import com.example.diplom.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface EventMapper {

    Event toEvent(EventRequest eventRequest);

    EventResponse toEventResponse(Event event);

    Event updateEvent(@MappingTarget Event event, EventRequest eventRequest);
}
