package dev.proleterler.product;

import dev.proleterler.product.model.Product;
import dev.proleterler.product.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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

    public int getProductQuantity(UUID productId) {
        return productRepository.getProductQuantity(productId);
    }

    public Map<UUID, BigDecimal> getPricePerProduct(Set<UUID> productIds) {
        return productRepository.getPricePerProduct(productIds);
    }
}
