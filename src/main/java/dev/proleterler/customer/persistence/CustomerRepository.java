package dev.proleterler.customer.persistence;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.generated.public_.tables.Customers;
import org.springframework.stereotype.Repository;

import static org.jooq.generated.public_.Tables.CUSTOMERS;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {
    private final DSLContext dslContext;

    public void test() {
        dslContext.select(CUSTOMERS)
    }

}
