package com.infomerica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infomerica.model.Product;
import com.infomerica.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.getAllProducts();
        log.info("Retrieved products : {}", products);
        return products;
    }

    public Product getProductById(int id) {
        log.info("Fetching product with ID: {}", id);
        Product product = productRepository.getProductById(id);
        if (product == null) {
            log.warn("Product with ID {} not found!", id);
        }
        return product;
    }

    public void addProduct(Product product) {
        log.info("Adding new product: {}", product.getName());
        productRepository.addProduct(product.getName(), product.getDescription(), product.getPrice());
    }

    public void updateProduct(int id, Product product) {
        log.info("Updating product with ID: {}", id);
        productRepository.updateProduct(id, product.getName(), product.getDescription(), product.getPrice());
    }

    public void deleteProduct(int id) {
        log.info("Deleting product with ID: {}", id);
        productRepository.deleteProduct(id);
    }
}
