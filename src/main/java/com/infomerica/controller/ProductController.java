package com.infomerica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.infomerica.model.Product;
import com.infomerica.service.ProductService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j // Enables SLF4J Logging
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/addProduct")
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		log.info("Received request to add product: {}", product.getName());
		try {
			productService.addProduct(product);
			log.info("Product {} added successfully!", product.getName());
			return ResponseEntity.ok("Product added successfully!");
		} catch (Exception e) {
			log.error("Error adding product: {}", product.getName(), e);
			return ResponseEntity.status(500).body("Error adding product!");
		}
	}

	@GetMapping("/getProductBy/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		log.info("Received request to fetch product with ID: {}", id);
		try {
			Product product = productService.getProductById(id);
			log.debug("Product details: {}", product);
			return ResponseEntity.ok(product);
		} catch (Exception e) {
			log.error("Error fetching product with ID: {}", id, e);
			return ResponseEntity.status(500).build();
		}
	}

	@GetMapping("/getallproducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		log.info("Received request to fetch all products");
		List<Product> products = productService.getAllProducts();
		log.info("Returning {} products", products.size());
		return ResponseEntity.ok(products);
	}

	@DeleteMapping("/deleteProductBy/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		log.info("Received request to delete product with ID: {}", id);
		try {
			productService.deleteProduct(id);
			log.info("Product with ID {} deleted successfully!", id);
			return ResponseEntity.ok("Product deleted successfully!");
		} catch (Exception e) {
			log.error("Error deleting product with ID: {}", id, e);
			return ResponseEntity.status(500).body("Error deleting product!");
		}
	}

	@PutMapping("/updateProductBy/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product product) {
		log.info("Received request to update product with ID: {}", id);
		try {
			productService.updateProduct(id, product);
			log.info("Product with ID {} updated successfully!", id);
			return ResponseEntity.ok("Product updated successfully!");
		} catch (Exception e) {
			log.error("Error updating product with ID: {}", id, e);
			return ResponseEntity.status(500).body("Error updating product!");
		}
	}

}
