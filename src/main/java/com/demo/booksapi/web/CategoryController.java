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

import com.demo.booksapi.domain.Category;
import com.demo.booksapi.domain.CategoryRepository;

@RestController
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	/*
	 * GET ALL
	 */
	@RequestMapping(value="/api/categories", method=RequestMethod.GET)
	public List<Category> getCategories() {
		return (List<Category>) categoryRepository.findAll();
	}
	
	
	/*
	 * POST
	 */
	@RequestMapping(value="/api/categories", method=RequestMethod.POST)
	public Category postCategory(@RequestBody Category newCategory) {
		return categoryRepository.save(newCategory);
	}
	
	
	/*
	 * DELETE
	 */
	@RequestMapping(value="/api/categories/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCategoty(@PathVariable Long id) {
		categoryRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
