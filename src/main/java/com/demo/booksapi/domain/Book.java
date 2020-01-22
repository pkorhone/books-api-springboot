package com.demo.booksapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@Entity
public class Book {
	
	@Id
	@JsonProperty("id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long bookId;
	
	@Column(nullable=false)
	private String title;
	
	private String author;
	private String description;
	private String ISBN;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "BookCategories",
			joinColumns = @JoinColumn(name = "bookId"),
			inverseJoinColumns = @JoinColumn(name = "categoryId"))
	private List<Category> categories;
	
	/*
	 * For better readability of JSON response, 'categories' attribute above is JsonIgnored
	 * (it will not be included in JSON). 
	 * instead, categories are mapped as an array of string objects below:
	 */
	
	@Transient
	@JsonProperty("categories")
	private List<String> categories() {
		List<String> result = new ArrayList<>();
		for (Category c : this.categories) {
			result.add(c.getName());
		}
		return result;
	}
	
	public void addCategory(Category category) {
		if (this.categories == null) {
			this.categories = new ArrayList<>();
		}
		this.categories.add(category);
	}
	
	public boolean matchesSimpleSearch(String searchTerm) {
		if (this.toString().toLowerCase().contains(searchTerm.toLowerCase())) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.title + ", " + this.author + ", " + this.description + ", " + this.ISBN;
	}

}
