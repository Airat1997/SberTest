package com.sber.controller;

import com.sber.model.Product;
import com.sber.repository.ProductRepository;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "products")
public class RestApiController {

    private final ProductRepository productRepository;

    public RestApiController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    ResponseEntity<Iterable<Product>> getProducts() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<Product> getProductById(@PathVariable("id") Product product) {
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Product> postProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    ResponseEntity<Product> putProduct(@PathVariable UUID id, @RequestBody Product product) {
        return !productRepository.existsById(id) ? new ResponseEntity<>(
                productRepository.save(product), HttpStatus.CREATED)
                : new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
