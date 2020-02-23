package com.okta.developer.jugtours.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okta.developer.jugtours.model.Group;
import com.okta.developer.jugtours.model.GroupRepository;

@RestController
@RequestMapping("/api")
public class GroupController {
	private final Logger logger = LoggerFactory.getLogger(GroupController.class);
	private GroupRepository groupRepository;

	public GroupController(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@GetMapping("/groups")
	Collection<Group> groups() {
		return groupRepository.findAll();

	}

	@GetMapping("/groups/{id}")
	ResponseEntity<Group> getGroup(@PathVariable Long id) {
		Optional<Group> group = groupRepository.findById(id);
		return group.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/group")
	ResponseEntity<Group> createGroup(@Valid @RequestBody Group group) throws URISyntaxException {
		Group result = groupRepository.save(group);
		return ResponseEntity.created(new URI("/api/group/"+ result.getId())).body(result);

	}

	@PutMapping("/group/{id}")
	ResponseEntity<Group> updateGroup(@Valid @RequestBody Group group) {
		Group result = groupRepository.save(group);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("/group/{id}")
	ResponseEntity<Group> deletegroup(@PathVariable Long id) {
		groupRepository.deleteById(id);
		return ResponseEntity.ok().build();

	}
}
