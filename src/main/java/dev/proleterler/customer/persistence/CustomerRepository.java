package dev.proleterler.customer.persistence;

import dev.proleterler.generated.jooq.tables.records.CustomersRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static dev.proleterler.generated.jooq.tables.Customers.CUSTOMERS;


@Repository
@RequiredArgsConstructor
public class CustomerRepository {
    private final DSLContext dslContext;

    public List<CustomersRecord> test() {
        return dslContext.selectFrom(CUSTOMERS).limit(10).fetch();
    }

}
