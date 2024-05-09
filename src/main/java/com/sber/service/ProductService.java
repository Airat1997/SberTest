package com.sber.service;

import com.sber.model.Product;
import com.sber.repository.ProductRepository;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private static final Logger LOGGER = LogManager.getLogger(ProductService.class);
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> findAll() {
        LOGGER.info("Attempting to find all products.");
        return productRepository.findAll();
    }

    public Product findById(UUID id) {
        LOGGER.info("Attempting to find product with ID: {}", id);
        Product product = productRepository.findById(id).orElse(null);
        if (product!= null) {
            LOGGER.info("Product found with ID: {}", id);
        } else {
            LOGGER.warn("No product found with ID: {}", id);
        }
        return product;
    }

    public Product save(Product product) {
        LOGGER.info("Attempting to save product: {}", product.getName());
        Product savedProduct = productRepository.save(product);
        LOGGER.info("Product saved with ID: {}", savedProduct.getId());
        return savedProduct;
    }

    public void deleteById(UUID id) {
        LOGGER.info("Attempting to delete product with ID: {}", id);
        productRepository.deleteById(id);
        LOGGER.info("Product deleted with ID: {}", id);
    }

    public boolean existsById(UUID id) {
        return productRepository.findById(id).isPresent();
    }
}
