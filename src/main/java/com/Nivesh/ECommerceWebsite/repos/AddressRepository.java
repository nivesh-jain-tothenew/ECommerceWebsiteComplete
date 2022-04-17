package com.Nivesh.ECommerceWebsite.repos;

import com.Nivesh.ECommerceWebsite.Entities.UserDetails.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findById(long id);
}
