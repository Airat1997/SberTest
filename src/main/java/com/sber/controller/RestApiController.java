package com.sber.controller;

import com.sber.model.Product;
import com.sber.repository.ProductRepository;
import com.sber.service.ProductService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с продуктами.
 */
@RestController
@RequestMapping(path = "products")
public class RestApiController {

    @Autowired
    private ProductService productService;

    /**
     * Возвращает все продукты.
     *
     * @return все продукты.
     */
    public RestApiController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<Iterable<Product>> getProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    /**
     * Возвращает продукт по его идентификатору.
     *
     * @return продукт с указанным идентификатором.
     */
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    /**
     * Создает новый продукт.
     *
     * @param product продукт для создания.
     * @return созданный продукт.
     */
    @PostMapping
    public ResponseEntity<Product> postProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    /**
     * Обновляет продукт по его идентификатору или добавляет новый.
     *
     * @param id      идентификатор продукта для обновления.
     * @param product продукт с обновленными данными или новый.
     * @return обновленный продукт или новый.
     */
    @PutMapping("{id}")
    public ResponseEntity<Product> putProduct(@PathVariable UUID id, @RequestBody Product product) {
        return !productService.existsById(id) ? new ResponseEntity<>(
                productService.save(product), HttpStatus.CREATED)
                : new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    /**
     * Удаляет продукт по его идентификатору.
     *
     * @param id идентификатор продукта для удаления.
     * @return статус операции.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
