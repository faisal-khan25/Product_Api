package com.tcs.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tcs.dto.ProductDTO;
import com.tcs.entity.Product;
import com.tcs.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository repository;
	@Autowired
	private ModelMapper mapper;

	@Override
	public ProductDTO create(ProductDTO dto) {
		if (repository.existsByName(dto.getName())) {
			throw new RuntimeException("Product already exists");
		}
		Product product = mapper.map(dto, Product.class);
		Product saved = repository.save(product);
		logger.info("Product created:{}", saved.getId());
		return mapper.map(saved, ProductDTO.class);
	}

	@Override
	public ProductDTO getById(Integer id) {
		Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("not found"));

		return mapper.map(product, ProductDTO.class);
	}

	@Override
	public Page<ProductDTO> getAll(int page, int size, String sort) {
		PageRequest pageable = PageRequest.of(page, size, Sort.by(sort));
		return repository.findAll(pageable).map(p -> mapper.map(p, ProductDTO.class));

	}

	@Override
	public List<ProductDTO> search(String keyword) {

		logger.info("Searching products with keyword: {}", keyword);

		List<Product> products = repository.findByNameContaining(keyword);

		return products.stream().map(product -> mapper.map(product, ProductDTO.class)).toList();
	}
}