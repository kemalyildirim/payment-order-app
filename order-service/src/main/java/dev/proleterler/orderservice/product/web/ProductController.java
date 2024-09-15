package dev.proleterler.orderservice.product.web;


import dev.proleterler.orderservice.product.BasketService;
import dev.proleterler.orderservice.product.ProductService;
import dev.proleterler.orderservice.product.model.Product;
import dev.proleterler.orderservice.product.web.command.AddBasketCommand;
import dev.proleterler.orderservice.product.web.command.SaveProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final BasketService basketService;

    @PostMapping
    public Product saveProduct(@RequestBody SaveProductCommand saveProductCommand) {
        return productService.saveProduct(saveProductCommand.name(), saveProductCommand.stockQuantity(), saveProductCommand.price());
    }

    @PostMapping("/basket")
    public ResponseEntity<?> addToBasket(@RequestBody AddBasketCommand addBasketCommand){
        try {
            basketService.addToMap(addBasketCommand.customerId(), addBasketCommand.productId(), addBasketCommand.quantity());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
