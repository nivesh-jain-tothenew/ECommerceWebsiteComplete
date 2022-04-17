package com.Nivesh.ECommerceWebsite.repos;

import com.Nivesh.ECommerceWebsite.Entities.Category.Category;
import com.Nivesh.ECommerceWebsite.Entities.Users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findById(long id);
}
