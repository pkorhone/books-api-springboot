package com.demo.booksapi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.booksapi.domain.Publisher;
import com.demo.booksapi.domain.PublisherRepository;

@RestController
public class PublisherController {
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	/*
	 * GET ALL
	 */
	@RequestMapping(value="/api/publishers", method=RequestMethod.GET)
	public List<Publisher> getPublishers() {
		return (List<Publisher>) publisherRepository.findAll();
	}
	
	/*
	 * POST
	 */
	@RequestMapping(value="/api/publishers", method=RequestMethod.POST)
	public Publisher postPublisher(@RequestBody Publisher newPublisher) {
		return publisherRepository.save(newPublisher);
	}
	
	/*
	 * DELETE
	 */
	@RequestMapping(value="/api/publishers/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deletePublisher(@PathVariable Long id) {
		publisherRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
