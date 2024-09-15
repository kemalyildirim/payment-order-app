package dev.proleterler.orderservice.customer.persistence;

import dev.proleterler.orderservice.customer.model.Customer;
import dev.proleterler.orderservice.generated.jooq.tables.records.CustomersRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static dev.proleterler.orderservice.generated.jooq.tables.Customers.CUSTOMERS;


@Repository
@RequiredArgsConstructor
public class CustomerRepository {
    private final DSLContext dslContext;

    public Customer save(String name, String email, Optional<String> phoneNumber) {
        return dslContext.insertInto(CUSTOMERS).columns(CUSTOMERS.NAME, CUSTOMERS.EMAIL, CUSTOMERS.PHONE_NUMBER)
                .values(name, email, phoneNumber.orElse(""))
                .returning()
                .fetchOneInto(Customer.class);
    }

    public List<CustomersRecord> test() {
        return dslContext.selectFrom(CUSTOMERS).limit(10).fetch();
    }

    public int getCustomerCount() {
        return dslContext.selectCount().from(CUSTOMERS).fetchOneInto(int.class);
    }
}
