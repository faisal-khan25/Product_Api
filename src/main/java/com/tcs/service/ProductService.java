package com.tcs.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.tcs.dto.ProductDTO;
@Service
public interface ProductService {

	public ProductDTO create(ProductDTO dto);

	public ProductDTO getById(Integer id);

	public Page<ProductDTO> getAll(int page, int size, String sort);

	public List<ProductDTO> search(String keyword);

}
