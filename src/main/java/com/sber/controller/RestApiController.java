package com.sber.controller;

import com.sber.model.Product;
import com.sber.repository.ProductRepository;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с продуктами.
 */
@RestController
@RequestMapping(path = "products")
public class RestApiController {

    private final ProductRepository productRepository;

    /**
     * Конструктор класса.
     *
     * @param productRepository репозиторий продуктов для работы с данными.
     */
    public RestApiController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Возвращает все продукты.
     *
     * @return все продукты.
     */
    @GetMapping
    public ResponseEntity<Iterable<Product>> getProducts() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Возвращает продукт по его идентификатору.
     *
     * @return продукт с указанным идентификатором.
     */
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Product product) {
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Создает новый продукт.
     *
     * @param product продукт для создания.
     * @return созданный продукт.
     */
    @PostMapping
    public ResponseEntity<Product> postProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    /**
     * Обновляет продукт по его идентификатору или добавляет новый.
     *
     * @param id     идентификатор продукта для обновления.
     * @param product продукт с обновленными данными или новый.
     * @return обновленный продукт или новый.
     */
    @PutMapping("{id}")
    public ResponseEntity<Product> putProduct(@PathVariable UUID id, @RequestBody Product product) {
        return !productRepository.existsById(id) ? new ResponseEntity<>(
                productRepository.save(product), HttpStatus.CREATED)
                : new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
    }

    /**
     * Удаляет продукт по его идентификатору.
     *
     * @param id идентификатор продукта для удаления.
     * @return статус операции.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
