package dev.proleterler.product;

import dev.proleterler.product.model.Product;
import dev.proleterler.product.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product saveProduct(String name, int stockQuantity, BigDecimal price) {
        return productRepository.saveProduct(name, stockQuantity, price);
    }

    public int getTotalProducts() {
        return productRepository.getTotalProducts();
    }
}
