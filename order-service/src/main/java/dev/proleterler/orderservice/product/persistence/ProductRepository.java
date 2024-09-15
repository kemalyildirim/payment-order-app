package dev.proleterler.orderservice.product.persistence;

import dev.proleterler.orderservice.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static dev.proleterler.orderservice.generated.jooq.tables.Products.PRODUCTS;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final DSLContext dslContext;

    public int getTotalProducts() {
        return dslContext.selectCount()
                .from(PRODUCTS)
                .fetchOne(0, int.class);
    }

    public Product saveProduct(String name, int stockQuantity, BigDecimal price) {
        return dslContext.insertInto(PRODUCTS)
                .columns(PRODUCTS.NAME, PRODUCTS.STOCK_QUANTITY, PRODUCTS.PRICE)
                .values(name, stockQuantity, price)
                .returning()
                    .fetchOneInto(Product.class);
    }

    public int getProductQuantity(UUID productId) {
        return dslContext.select(PRODUCTS.STOCK_QUANTITY).from(PRODUCTS).where(PRODUCTS.ID.eq(productId)).fetchOneInto(int.class);
    }

    public Map<UUID, BigDecimal> getPricePerProduct(Set<UUID> productIds) {
        return dslContext.select(PRODUCTS.ID, PRODUCTS.PRICE).from(PRODUCTS).
                where(PRODUCTS.ID.in(productIds)).
                collect(Collectors.
                        toMap(r -> r.into(PRODUCTS.ID).into(UUID.class), r -> r.into(PRODUCTS.PRICE).into(BigDecimal.class)));
    }
}
