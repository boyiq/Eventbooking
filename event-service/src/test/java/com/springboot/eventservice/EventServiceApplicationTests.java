package com.springboot.eventservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.assertions.Assertions;
import com.springboot.eventservice.repository.EventRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.springboot.eventservice.model.Event;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class EventServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private EventRepository eventRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
 	@Test
	void shouldAddEvent() throws Exception {
		String mappedNewEvent = objectMapper.writeValueAsString(newEvent1);
		mockMvc.perform(MockMvcRequestBuilders.post("/event")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mappedNewEvent)).andExpect(status().isCreated());
		Assertions.assertTrue(eventRepository.findAll().size() ==1 );
		Assertions.assertNotNull(eventRepository.findOneByName("World Cup final"));
	}

	@Before
	void setUp() {

	}
	@Test
	void shouldGetAllEvents() throws Exception {

	}

	private Event newEvent1 = Event.builder()
				.name("World Cup final")
				.address("Sports stadium")
				.city("London")
				.price(200)
				.build();
	private Event newEvent2 = Event.builder()
			.name("Art show")
			.address("City gallery")
			.city("New York City")
			.price(50)
			.build();
}
