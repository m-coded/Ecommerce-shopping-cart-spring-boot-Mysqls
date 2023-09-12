package com.ecommerce2.ecommerce2.LocalRepository;

import org.springframework.data.repository.ListCrudRepository;

import com.ecommerce2.ecommerce2.model.Product;

public interface ProductRepository extends ListCrudRepository<Product,Long> {

}
