package dev.proleterler.product;

import dev.proleterler.product.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketService {


    public static final Map<UUID, Map<UUID, Integer>> basketMap = new ConcurrentHashMap<>();
    public final ProductService productService;

    public void addToMap(UUID customerId, UUID productID, int quantity){
        var customerBasket = basketMap.get(customerId);
        if(customerBasket != null) {
            if(customerBasket.containsKey(productID)) {
                log.error("quantity: {}", productService.getProductQuantity(productID));
                if(productService.getProductQuantity(productID) > customerBasket.get(productID) + quantity) {
                    customerBasket.put(productID, customerBasket.get(productID) + quantity);
                } else {
                    throw new RuntimeException("there is no enough quantity");
                }
            } else {
                if(productService.getProductQuantity(productID) > quantity) {
                    customerBasket.put(productID, quantity);
                } else {
                    throw new RuntimeException("there is no enough quantity");
                }

            }
        } else {
            log.error("quantity: {}", productService.getProductQuantity(productID));
            if(productService.getProductQuantity(productID) > quantity) {
                basketMap.put(customerId, new HashMap<>() {{
                    put(productID, quantity);
                }});
            } else {
                throw new RuntimeException("there is no enough quantity");
            }
        }
    }

    public Map<UUID, Integer> getBasketMap(UUID customerId){
        var customerBasket = basketMap.get(customerId);
        if(customerBasket == null) {
            throw new RuntimeException("The customer does not have basket");
        }
        return customerBasket;
    }

}
