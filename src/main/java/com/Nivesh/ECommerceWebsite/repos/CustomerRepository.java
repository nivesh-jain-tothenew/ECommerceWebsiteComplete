package com.Nivesh.ECommerceWebsite.repos;

import com.Nivesh.ECommerceWebsite.Entities.Users.Customer;
import com.Nivesh.ECommerceWebsite.Entities.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //User findByEmail(String email);
    Customer findById(long id);
}
