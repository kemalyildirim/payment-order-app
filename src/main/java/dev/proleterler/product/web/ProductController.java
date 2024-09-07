package dev.proleterler.product.web;


import dev.proleterler.product.BasketService;
import dev.proleterler.product.ProductService;
import dev.proleterler.product.model.Product;
import dev.proleterler.product.web.command.AddBasketCommand;
import dev.proleterler.product.web.command.SaveProductCommand;
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
    public ResponseEntity<Void> addToBasket(@RequestBody AddBasketCommand addBasketCommand){
        basketService.addToMap(addBasketCommand.customerId(), addBasketCommand.productId(), addBasketCommand.quantity());
        return new ResponseEntity<Void> (HttpStatus.OK);
    }
}
