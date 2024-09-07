package dev.proleterler.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public void addToMap(UUID customerId, UUID productID, int quantity){
        var customerBasket = basketMap.get(customerId);
        if(customerBasket != null) {
            if(customerBasket.containsKey(productID)) {
                customerBasket.put(productID, customerBasket.get(productID)+quantity);
            } else {
                customerBasket.put(productID, quantity);
            }
        } else {
            basketMap.put(customerId, Map.of(productID, quantity));
        }
        log.info("exit from save basket service {}", basketMap);
    }

}
