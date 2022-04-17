package com.Nivesh.ECommerceWebsite.security;

import com.Nivesh.ECommerceWebsite.Entities.UserDetails.Role;
import com.Nivesh.ECommerceWebsite.Entities.Users.User;
import com.Nivesh.ECommerceWebsite.Entities.dto.UserDTO;
import com.Nivesh.ECommerceWebsite.Entities.pass.PasswordRequest;
import com.Nivesh.ECommerceWebsite.exception.UserNotActiveException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface UserService {

    User userConvertDtoToEntity(UserDTO userDTO);

    UserDTO userConvertEntityTODto(User user);

    boolean checkPasswordAndConfirmPassword(String password , String confirmPassword);

    String getUserName(String username);

    String getPassword(String username);

    Set<Role> getRoles(String username);

    void forgetPasswordSendMail(String email) throws UserNotActiveException;

    @Transactional
    String updatePassword(String token, PasswordRequest passwordRequest);
}
