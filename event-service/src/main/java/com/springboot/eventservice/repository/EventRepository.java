package com.springboot.eventservice.repository;

import com.springboot.eventservice.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface EventRepository extends MongoRepository<Event, Integer>{

}
