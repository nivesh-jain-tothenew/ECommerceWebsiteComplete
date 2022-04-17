package com.Nivesh.ECommerceWebsite.repos;

import com.Nivesh.ECommerceWebsite.Entities.Product.Product;
import com.Nivesh.ECommerceWebsite.Entities.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findById(long id);
    List<Product> findByNameContaining(String name);
}
