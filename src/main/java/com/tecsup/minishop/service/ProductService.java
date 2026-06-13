package com.tecsup.minishop.service;

import com.tecsup.minishop.model.Product;
import com.tecsup.minishop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    // PASO 5.2 — Definición de constantes nombradas para eliminar números mágicos
    private static final double MIN_PRICE_THRESHOLD = 0.0;
    private static final int MIN_STOCK_THRESHOLD = 0;

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        // Uso de las constantes refactorizadas para mejorar la legibilidad
        if (product.getPrice() <= MIN_PRICE_THRESHOLD) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        if (product.getStock() < MIN_STOCK_THRESHOLD) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }
}