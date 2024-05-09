package com.sber.service;

import com.sber.model.Product;
import com.sber.repository.ProductRepository;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с продуктами. Обеспечивает основные операции CRUD (создание, чтение,
 * обновление, удаление) для объектов Product.
 */
@Service
public class ProductService {

    /**
     * Репозиторий для доступа к данным продуктов.
     */
    private final ProductRepository productRepository;
    /**
     * Логгер для записи сообщений.
     */

    private static final Logger LOGGER = LogManager.getLogger(ProductService.class);

    /**
     * Конструктор сервиса, инициализирует репозиторий продуктов.
     *
     * @param productRepository Репозиторий продуктов для доступа к данным.
     */
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Возвращает все продукты из репозитория.
     *
     * @return коллекция всех продуктов.
     */
    public Iterable<Product> findAll() {
        LOGGER.info("Attempting to find all products.");
        return productRepository.findAll();
    }

    /**
     * Находит продукт по его идентификатору.
     *
     * @param id Идентификатор продукта.
     * @return Product - найденный продукт или null, если продукт не найден.
     */
    public Product findById(UUID id) {
        LOGGER.info("Attempting to find product with ID: {}", id);
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            LOGGER.info("Product found with ID: {}", id);
        } else {
            LOGGER.warn("No product found with ID: {}", id);
        }
        return product;
    }

    /**
     * Сохраняет продукт в репозитории.
     *
     * @param product Продукт для сохранения.
     * @return Product - сохраненный продукт.
     */
    public Product save(Product product) {
        LOGGER.info("Attempting to save product: {}", product.getName());
        Product savedProduct = productRepository.save(product);
        LOGGER.info("Product saved with ID: {}", savedProduct.getId());
        return savedProduct;
    }

    /**
     * Удаляет продукт по его идентификатору.
     *
     * @param id Идентификатор продукта для удаления.
     */
    public void deleteById(UUID id) {
        LOGGER.info("Attempting to delete product with ID: {}", id);
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            LOGGER.info("Product deleted with ID: {}", id);
        } else {
            LOGGER.warn("Product with ID: {} not found", id);
        }
    }

    /**
     * Проверяет наличие продукта по его идентификатору.
     *
     * @param id Идентификатор продукта.
     * @return boolean - true, если продукт существует, иначе false.
     */
    public boolean existsById(UUID id) {
        return productRepository.findById(id).isPresent();
    }
}
