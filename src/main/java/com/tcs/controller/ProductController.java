package com.tcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.dto.ProductDTO;
import com.tcs.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")

public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody ProductDTO dto) {
		return ResponseEntity.ok(service.create(dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getById(id));
	}

	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam int page, @RequestParam int size, @RequestParam String sort) {

		return ResponseEntity.ok(service.getAll(page, size, sort));
	}

	
}
