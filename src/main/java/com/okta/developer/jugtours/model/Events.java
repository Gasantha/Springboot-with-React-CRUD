package com.okta.developer.jugtours.model;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Events {
	@Id
	@GeneratedValue
	private Long id;
	private Instant date;
	private String title;
	private String description;
	@ManyToMany
	private Set<User> attendees;

}
