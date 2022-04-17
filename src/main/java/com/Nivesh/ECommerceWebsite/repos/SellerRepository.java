package com.Nivesh.ECommerceWebsite.repos;

import com.Nivesh.ECommerceWebsite.Entities.Users.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    //Seller findByEmail(String email);
}
