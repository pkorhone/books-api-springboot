package com.demo.booksapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.booksapi.domain.Book;
import com.demo.booksapi.domain.BookRepository;
import com.demo.booksapi.domain.Category;
import com.demo.booksapi.domain.CategoryRepository;

@SpringBootApplication
public class BooksApiApplication {

	/*
	 * Main method (runs Spring Boot application)
	 */
	public static void main(String[] args) {
		SpringApplication.run(BooksApiApplication.class, args);
	}
	
	/*
	 * Command line runner for testing purposes
	 */
	@Bean
	public CommandLineRunner clRunner(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			
			// save some categories
			
			Category cat1 = new Category();
			cat1.setName("Thriller");
			categoryRepository.save(cat1);
			
			Category cat2 = new Category();
			cat2.setName("Comedy");
			categoryRepository.save(cat2);
			
			Category cat3 = new Category();
			cat3.setName("Business");
			categoryRepository.save(cat3);
			
			Category cat4 = new Category();
			cat4.setName("Romance");
			categoryRepository.save(cat4);
			
			// save some books
			
			Book book1 = new Book();
			book1.setTitle("book One in series");
			book1.setAuthor("Aku Ankka");
			book1.setDescription("Opi englantia akun ja mikin kanssa");
			book1.setISBN("1236547896");
			book1.addCategory(cat1);
			book1.addCategory(cat2);
			bookRepository.save(book1);
			
			Book book2 = new Book();
			book2.setTitle("Another long book");
			book2.setAuthor("Hannu Hanhi");
			book2.setDescription("elämä on laiffii");
			book2.setISBN("987654321");
			book2.addCategory(cat3);
			book2.addCategory(cat4);
			bookRepository.save(book2);
			
			Book book3 = new Book();
			book3.setTitle("This is a third book");
			book3.setAuthor("Mikki Hiiri");
			book3.setDescription("super electric adventure");
			book3.setISBN("456987321");
			book3.addCategory(cat1);
			book3.addCategory(cat3);
			bookRepository.save(book3);
			
		};
	}

}
