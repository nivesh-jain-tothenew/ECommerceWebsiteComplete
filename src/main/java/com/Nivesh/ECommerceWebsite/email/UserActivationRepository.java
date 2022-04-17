package com.Nivesh.ECommerceWebsite.email;

import com.Nivesh.ECommerceWebsite.Entities.Users.User;
import com.Nivesh.ECommerceWebsite.Entities.Users.UserActivation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface UserActivationRepository extends CrudRepository<UserActivation, Long> {

    UserActivation findByConfirmationToken(String confirmationToken);

    Date findByExpiryDate(Date expiryDate);

    UserActivation findByUser(User user);

    @Modifying
    @Query(value = "delete from UserActivation where confirmation_token =:token")
    void deleteConfirmationToken(@Param("token")String token);
}
