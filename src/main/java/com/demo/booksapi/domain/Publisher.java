package com.demo.booksapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Publisher {

	@Id
	@JsonProperty("id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long publisherId;
	
	@Column(nullable=false)
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="publisher")
	@JsonIgnore
	private List<Book> books;
	
	public void addBook(Book newBook) {
		if (this.books == null) {
			this.books = new ArrayList<>();
		}
		this.books.add(newBook);
	}
}
