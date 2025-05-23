package com.example.diplom.consumer;

import com.example.diplom.dto.request.EventRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventConsumer {

//    @KafkaListener(topics = "event-topic", groupId = "event-groupId", concurrency = "3", containerFactory = "myListenerContainerFactory")
    public void listen(EventRequest eventRequest) {
        log.info(eventRequest.toString());
    }
}
