package com.ecommerce2.ecommerce2.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce2.ecommerce2.Service.ProductService;
import com.ecommerce2.ecommerce2.model.Product;

@RestController
@RequestMapping("/product")
public class ProductController {

  /** The Product Service. */
  private ProductService productService;

  /**
   * Constructor for spring injection.
   * @param productService
   */
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  /**
   * Gets the list of products available.
   * @return The list of products.
   */
  @GetMapping
  public List<Product> getProducts() {
    return productService.getProducts();
  }

}
