package com.okta.developer.jugtours;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.okta.developer.jugtours.model.Events;
import com.okta.developer.jugtours.model.Group;
import com.okta.developer.jugtours.model.GroupRepository;

@Component
public class Initializer implements CommandLineRunner {

	private final GroupRepository repository;

	@Override
	public void run(String... args) throws Exception {

		/*Stream.of("group1","group2","group3","group4").
		forEach(name->repository.save(new Group(name)));
		*/Stream.of("Denver JUG", "Utah JUG", "Seattle JUG",
                "Richmond JUG").forEach(name ->
                repository.save(new Group(name))
        );
		
		Group djug=repository.findByName("Denver JUG");
		Events e = Events.builder().title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();	
		
	    djug.setEvents(Collections.singleton(e));
	    repository.save(djug);
	    repository.findAll().forEach(System.out::println);
	}
	

	public Initializer(GroupRepository repsository) {
		super();
		this.repository = repsository;
	}

}
