package dev.proleterler.product.persistence;

import dev.proleterler.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

import static dev.proleterler.generated.jooq.tables.Products.PRODUCTS;

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
}
