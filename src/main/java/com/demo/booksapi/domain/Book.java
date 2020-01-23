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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@JsonPropertyOrder({
	"id",
	"title",
	"author",
	"description",
	"categories",
	"publisher",
	"publishedYear", 
	"imageLink"
})
public class Book {
	
	@Id
	@JsonProperty("id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long bookId;
	
	@Column(nullable=false)
	private String title;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "authorId")
	private Author author;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "publisherId")
	private Publisher publisher;
	
	private int publishedYear;
	
	@Lob
	private String description;
	
	private String imageLink;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "BookCategories",
			joinColumns = @JoinColumn(name = "bookId"),
			inverseJoinColumns = @JoinColumn(name = "categoryId"))
	private List<Category> categories;
	
	/*
	 * For better readability of JSON response, 'author', 'publisher' and 'categories' attributes 
	 * above are JsonIgnored (they will not be included in JSON).
	 * instead, author and publisher are represented only by their names (String) and 
	 * categories are mapped as an array of string objects below:
	 */
	
	@Transient
	@JsonProperty("author")
	private String authorName() {
		return this.author.getName();
	}
	
	@Transient
	@JsonProperty("publisher")
	private String publisherName() {
		return this.publisher.getName();
	}
	
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
		return this.title + ", " + this.author.getName() + ", " + this.description;
	}

}
