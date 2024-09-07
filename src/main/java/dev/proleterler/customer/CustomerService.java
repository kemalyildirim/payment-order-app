package dev.proleterler.customer;

import dev.proleterler.customer.model.Customer;
import dev.proleterler.customer.persistence.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer saveCustomer(String name, String email, Optional<String> phoneNumber) {
        return customerRepository.save(name, email, phoneNumber);
    }

    public int getCustomerCount() {
        return customerRepository.getCustomerCount();
    }

}
