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
 * REST API контроллер для работы с продуктами.
 * Обеспечивает HTTP запросы для получения, создания, обновления и удаления продуктов.
 */
@RestController
@RequestMapping(path = "products")
public class RestApiController {

    /**
     * Сервис для работы с продуктами.
     */
    @Autowired
    private final ProductService productService;

    /**
     * Конструктор контроллера, инициализирует сервис продуктов.
     *
     * @param productService Сервис продуктов для обработки бизнес-логики.
     */
    public RestApiController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Возвращает все продукты в виде списка.
     *
     * @return ResponseEntity - список всех продуктов с HTTP статусом OK.
     */
    @GetMapping
    public ResponseEntity<Iterable<Product>> getProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    /**
     * Возвращает продукт по его идентификатору.
     *
     * @param id Идентификатор продукта.
     * @return ResponseEntity - найденный продукт с HTTP статусом OK.
     */
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    /**
     * Создает новый продукт.
     *
     * @param product Продукт для создания.
     * @return ResponseEntity - созданный продукт с HTTP статусом CREATED.
     */
    @PostMapping
    public ResponseEntity<Product> postProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    /**
     * Обновляет существующий продукт или создает новый, если продукт с таким идентификатором не существует.
     *
     * @param id Идентификатор продукта.
     * @param product Продукт для обновления или создания.
     * @return ResponseEntity - обновленный или созданный продукт с соответствующим HTTP статусом.
     */
    @PutMapping("{id}")
    public ResponseEntity<Product> putProduct(@PathVariable UUID id, @RequestBody Product product) {
        return!productService.existsById(id)? new ResponseEntity<>(
                productService.save(product), HttpStatus.CREATED)
                : new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    /**
     * Удаляет продукт по его идентификатору.
     *
     * @param id Идентификатор продукта для удаления.
     * @return ResponseEntity - пустой ответ с HTTP статусом OK.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
