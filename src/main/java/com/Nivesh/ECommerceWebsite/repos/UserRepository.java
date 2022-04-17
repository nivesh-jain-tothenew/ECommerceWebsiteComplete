package com.Nivesh.ECommerceWebsite.repos;

import com.Nivesh.ECommerceWebsite.Entities.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from user", nativeQuery = true)
    User findByEmail(String email);

}
