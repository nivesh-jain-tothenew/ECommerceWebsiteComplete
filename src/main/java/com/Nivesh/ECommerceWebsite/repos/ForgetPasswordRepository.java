package com.Nivesh.ECommerceWebsite.repos;

import com.Nivesh.ECommerceWebsite.Entities.Users.User;
import com.Nivesh.ECommerceWebsite.Entities.pass.ForgetPassword;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ForgetPasswordRepository extends CrudRepository<ForgetPassword, Long> {

    ForgetPassword findByConfirmationToken(String confirmationToken);

    Date findByExpiryDate(Date expiryDate);

    ForgetPassword findByUser(User user);

    @Modifying
    @Query(value = "delete from ForgetPassword where confirmation_token =:token")
    void deleteConfirmationToken(@Param("token")String token);
}
