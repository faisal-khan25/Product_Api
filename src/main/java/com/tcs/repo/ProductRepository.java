package com.tcs.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	boolean existsByName(String name);

	Page<Product> findByCategory(String category, Pageable pageable);

	List<Product> findByNameContaining(String keyword);
}
