package com.Nivesh.ECommerceWebsite.security;

import com.Nivesh.ECommerceWebsite.Entities.UserDetails.Role;
import com.Nivesh.ECommerceWebsite.Entities.Users.User;
import com.Nivesh.ECommerceWebsite.Entities.dto.UserDTO;
import com.Nivesh.ECommerceWebsite.Entities.pass.ForgetPassword;
import com.Nivesh.ECommerceWebsite.Entities.pass.PasswordRequest;
import com.Nivesh.ECommerceWebsite.email.EmailSendService;
import com.Nivesh.ECommerceWebsite.exception.PasswordAndConfirmPasswordMismatchException;
import com.Nivesh.ECommerceWebsite.exception.UserNotActiveException;
import com.Nivesh.ECommerceWebsite.repos.ForgetPasswordRepository;
import com.Nivesh.ECommerceWebsite.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Set;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ForgetPasswordRepository forgetPasswordRepository;

    @Autowired
    private EmailSendService emailSendService;

    @Override
    public User userConvertDtoToEntity(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setPassword(userDTO.getPassword());
        user.setMiddleName(userDTO.getMiddleName());
        user.setConfirmPassword(userDTO.getConfirmPassword());
        user.setPASSWORD_UPDATE_DATE(new Date());
        user.setLastName(userDTO.getLastName());
        return user;
    }

    @Override
    public UserDTO userConvertEntityTODto(User user) {
        return new UserDTO(user.getUserId(), user.getEmail(), user.getFirstName(), user.getMiddleName(), user.getLastName(),
                user.getRoles(), user.getPASSWORD_UPDATE_DATE());
    }

    @Override
    public boolean checkPasswordAndConfirmPassword(String password, String confirmPassword) {
        if (confirmPassword.equals(password))
            return true;
        else
            throw new PasswordAndConfirmPasswordMismatchException("Password and confirm password donot match," +
                    "\n check and try again");
    }

    @Override
    public String getUserName(String username) {
        User user = userRepository.findByEmail(username);
        String username1 = user.getEmail();
        return username1;
    }

    @Override
    public String getPassword(String username) {
        User user = userRepository.findByEmail(username);
        String password = user.getPassword();
        return password;
    }

    @Override
    public Set<Role> getRoles(String username) {
        User user = userRepository.findByEmail(username);
        Set<Role> roles = user.getRoles();
        return roles;
    }

    @Override
    public void forgetPasswordSendMail(String email) throws UserNotActiveException {

        User user = userRepository.findByEmail(email);
        if (user != null) {
            if (user.isActive()) {
                ForgetPassword forgetPassword = new ForgetPassword(user);
                forgetPasswordRepository.save(forgetPassword);
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(user.getEmail());
                message.setFrom("test@gmail.com");
                message.setSubject("PASSWORD UPDATION");
                message.setText("To change your password, please click here:" + "http://localhost:9092/forgetPassword/updatePassword?token="
                        + forgetPassword.getConfirmationToken());
                emailSendService.sendEmail(message);
            } else
                throw new UserNotActiveException("User is not active");
        } else
            throw new EntityNotFoundException("entity not found");
    }

    @Override
    public String updatePassword(String token, PasswordRequest passwordRequest) {
        ForgetPassword forgetPassword = forgetPasswordRepository.findByConfirmationToken(token);
        if (forgetPassword != null) {
            Date expiryDate = forgetPassword.getExpiryDate();
            if (expiryDate.before(new Date())) {
                forgetPasswordRepository.deleteConfirmationToken(token);
                User user = forgetPassword.getUser();
                User user1 = userRepository.getById(user.getUserId());
                forgetPasswordSendMail(user1.getEmail());
                String tokenExpired = "TOKEN EXPIRED+\n mail has been sent to your email id with another token link";
                return tokenExpired;
            } else {
                User user = forgetPassword.getUser();
                System.out.println(user.getUserId());
                User user1 = userRepository.getById(user.getUserId());
                checkPasswordAndConfirmPassword(passwordRequest.getPassword(), passwordRequest.getConfirmPassword());
                user1.setPassword(passwordRequest.getPassword());
                userRepository.save(user1);
                forgetPasswordRepository.deleteConfirmationToken(token);
                return "password changed";
            }
        } else {
            String tokenInvalid = "Token invalid";
            return tokenInvalid;
        }
    }
}