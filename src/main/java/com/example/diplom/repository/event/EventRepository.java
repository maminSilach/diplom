package com.example.diplom.repository.event;


import com.example.diplom.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {


    boolean existsByName(String name);
}
