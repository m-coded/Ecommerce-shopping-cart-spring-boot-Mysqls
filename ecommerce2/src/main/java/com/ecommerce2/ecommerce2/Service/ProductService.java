package com.ecommerce2.ecommerce2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce2.ecommerce2.LocalRepository.ProductRepository;
import com.ecommerce2.ecommerce2.model.Product;

@Service
public class ProductService {
	 @Autowired
	 ProductRepository repository;
	 
	 public List<Product> getProducts() {
		    return repository.findAll();

}}
