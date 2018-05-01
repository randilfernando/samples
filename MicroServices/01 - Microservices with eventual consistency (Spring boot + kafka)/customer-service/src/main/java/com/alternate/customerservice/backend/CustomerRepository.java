package com.alternate.customerservice.backend;

import com.alternate.customerservice.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
