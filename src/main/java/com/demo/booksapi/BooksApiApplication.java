package com.demo.booksapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.booksapi.domain.Author;
import com.demo.booksapi.domain.AuthorRepository;
import com.demo.booksapi.domain.Book;
import com.demo.booksapi.domain.BookRepository;
import com.demo.booksapi.domain.Category;
import com.demo.booksapi.domain.CategoryRepository;
import com.demo.booksapi.domain.Publisher;
import com.demo.booksapi.domain.PublisherRepository;

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
	public CommandLineRunner clRunner(
			BookRepository bookRepository, 
			AuthorRepository authorRepository, 
			CategoryRepository categoryRepository,
			PublisherRepository publisherRepository) {
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
			
			// save some authors
			
			Author auth1 = new Author();
			auth1.setName("Aku Ankka");
			authorRepository.save(auth1);
			
			Author auth2 = new Author();
			auth2.setName("Hannu Hanhi");
			authorRepository.save(auth2);
			
			Author auth3 = new Author();
			auth3.setName("Mikki Hiiri");
			authorRepository.save(auth3);
			
			// save some publishers
			
			Publisher pub1 = new Publisher();
			pub1.setName("Otava");
			publisherRepository.save(pub1);
			
			Publisher pub2 = new Publisher();
			pub2.setName("WSOY");
			publisherRepository.save(pub2);
			
			// save some books
			
			Book book1 = new Book();
			book1.setTitle("book One in series");
			book1.setAuthor(auth1);
			book1.setDescription("Opi englantia akun ja mikin kanssa");
			book1.addCategory(cat1);
			book1.addCategory(cat2);
			book1.setPublisher(pub1);
			
			bookRepository.save(book1);
			auth1.addBook(book1);
			authorRepository.save(auth1);
			pub1.addBook(book1);
			publisherRepository.save(pub1);
			
			Book book2 = new Book();
			book2.setTitle("Another long book");
			book2.setAuthor(auth2);
			book2.setDescription("elämä on laiffii");
			book2.addCategory(cat3);
			book2.addCategory(cat4);
			book2.setPublisher(pub2);
			
			bookRepository.save(book2);
			auth2.addBook(book2);
			authorRepository.save(auth2);
			pub2.addBook(book2);
			publisherRepository.save(pub2);
			
			Book book3 = new Book();
			book3.setTitle("This is a third book");
			book3.setAuthor(auth3);
			book3.setDescription("super electric adventure");
			book3.addCategory(cat1);
			book3.addCategory(cat3);
			book3.setPublisher(pub2);
			
			bookRepository.save(book3);
			auth3.addBook(book3);
			authorRepository.save(auth3);
			pub2.addBook(book3);
			publisherRepository.save(pub2);
		};
	}
	

}
