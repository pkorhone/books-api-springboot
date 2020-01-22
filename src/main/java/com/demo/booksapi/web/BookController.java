package com.demo.booksapi.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.booksapi.domain.Book;
import com.demo.booksapi.domain.BookRepository;

@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	/*
	 * SIMPLE SEARCH (MATCH ANY ATTRIBUTE)
	 */
	@RequestMapping(value="/api/search")
	public List<Book> simpleSearch(@RequestParam String term) {
		List<Book> response = new ArrayList<>();
		for (Book b : bookRepository.findAll()) {
			if (b.matchesSimpleSearch(term)) {
				response.add(b);
			}
		}
		return response;
	}
	
	
	/*
	 * GET ALL
	 */
	@RequestMapping(value="/api/books", method=RequestMethod.GET)
	public List<Book> getBooks() {
		return (List<Book>) bookRepository.findAll();
	}
	
	
	/*
	 * GET ONE
	 */
	@RequestMapping(value="/api/books/{id}", method=RequestMethod.GET)
	public Book getBook(@PathVariable Long id) {
		return bookRepository.findById(id).get();
	}
	
	
	/*
	 * POST
	 */
	@RequestMapping(value="/api/books", method=RequestMethod.POST)
	public Book postBook(@RequestBody Book newBook) {
		return bookRepository.save(newBook);
	}
	
	
	/*
	 * PUT
	 */
	@RequestMapping(value="/api/books/{id}", method=RequestMethod.PUT)
	public Book updateBook(@RequestBody Book updatedBook, @PathVariable Long id) {
		updatedBook.setBookId(id);
		return bookRepository.save(updatedBook);
	}
	
	
	/*
	 * DELETE
	 */
	@RequestMapping(value="api/books/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteBook(@PathVariable Long id) {
		bookRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
