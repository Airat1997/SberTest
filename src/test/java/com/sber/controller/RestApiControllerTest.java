package com.sber.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Test.*;
import com.sber.model.Product;
import com.sber.service.ProductService;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;


@SpringBootTest
class RestApiControllerTest {
    @Autowired
    private ProductService productService;
    String uuidString = "0bc1978c-a219-4ccc-adfc-3157910002df";
    UUID uuid = UUID.fromString(uuidString);
//    @BeforeEach
//    void setUp() throws Exception {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    ('123e4567-e89b-12d3-a456-426614174000', 'Product Name 1', 'This is a description for Product 1.', 19.99),
//    ('123e4567-e89b-12d3-a456-426614174001', 'Product Name 2', 'This is a description for Product 2.', 29.99),
//    ('123e4567-e89b-12d3-a456-426614174002', 'Product Name 3', 'This is a description for Product 3.', 39.99);
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    @Test
    void getProducts() throws Exception {
        Iterable<Product> list = productService.findAll();
        assertEquals(3, list.spliterator().estimateSize());
    }

    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    @Test
    void postProduct() throws Exception {
        Product product = new Product(uuid, "Test","Test description",123.0);
        productService.save(product);
        assertNotNull(productService.findById(uuid));
    }
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    @Test
    void putProduct() throws Exception {
        Product product = new Product(uuid, "Test","Test description",123.0);
        productService.save(product);
        assertNotNull(productService.findById(uuid));
        product.setDescription("new Test");
        productService.save(product);
        assertEquals(productService.findById(uuid).getDescription(), "new Test");
    }

    @Test
    void deleteProduct() throws Exception {
        String uuidString = "123e4567-e89b-12d3-a456-426614174000";
        UUID uuid2 = UUID.fromString(uuidString);
        productService.deleteById(uuid2);
        assertEquals(productService.findById(uuid2), null);
    }
}