package com.infomerica.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.infomerica.config.QueryLoader;
import com.infomerica.model.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j // Enables logging with SLF4J
@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final QueryLoader queryLoader;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate, QueryLoader queryLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.queryLoader = queryLoader;
    }

    private final RowMapper<Product> rowMapper = (rs, rowNum) -> 
        new Product(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getDouble("price"));

    public List<Product> getAllProducts() {
        log.info("Fetching all products...");
        try {
            List<Product> products = jdbcTemplate.query(queryLoader.getQuery("query.select.all"), rowMapper);
            log.info("Retrieved {} products", products.size());
            return products;
        } catch (Exception e) {
            log.error("Error fetching all products", e);
            throw e;
        }
    }

    public Product getProductById(int id) {
        log.info("Fetching product with ID: {}", id);
        try {
            Product product = jdbcTemplate.queryForObject(queryLoader.getQuery("query.select.byId"), rowMapper, id);
            return product;
        } catch (Exception e) {
            log.error("Error fetching product with ID: {}", id, e);
            throw e;
        }
    }

    public void addProduct(String name, String description, double price) {
        log.info("Adding new product: {}", name);
        try {
            jdbcTemplate.update(queryLoader.getQuery("query.insert"), name, description, price);
            log.info("Product {} added successfully", name);
        } catch (Exception e) {
            log.error("Error adding product: {}", name, e);
            throw e;
        }
    }

    public void updateProduct(int id, String name, String description, double price) {
        log.info("Updating product with ID: {}", id);
        try {
            jdbcTemplate.update(queryLoader.getQuery("query.update"), name, description, price, id);
            log.info("Product with ID {} updated successfully", id);
        } catch (Exception e) {
            log.error("Error updating product with ID: {}", id, e);
            throw e;
        }
    }

    public void deleteProduct(int id) {
        log.info("Deleting product with ID: {}", id);
        try {
            jdbcTemplate.update(queryLoader.getQuery("query.delete"), id);
            log.info("Product with ID {} deleted successfully", id);
        } catch (Exception e) {
            log.error("Error deleting product with ID: {}", id, e);
            throw e;
        }
    }
}
