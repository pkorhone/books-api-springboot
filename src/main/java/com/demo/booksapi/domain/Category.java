package com.demo.booksapi.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Category {

	@Id
	@JsonProperty("id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long categoryId;
	
	@Column(nullable=false, unique=true)
	private String name;
	
	@ManyToMany(mappedBy="categories")
	@JsonIgnore
	private List<Book> books;
}
