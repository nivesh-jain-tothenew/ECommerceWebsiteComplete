package com.Nivesh.ECommerceWebsite.security;

import com.Nivesh.ECommerceWebsite.Entities.UserDetails.Role;
import com.Nivesh.ECommerceWebsite.Entities.Users.Customer;
import com.Nivesh.ECommerceWebsite.Entities.Users.User;
import com.Nivesh.ECommerceWebsite.Entities.Users.UserActivation;
import com.Nivesh.ECommerceWebsite.Entities.dto.AddressDTO;
import com.Nivesh.ECommerceWebsite.Entities.dto.CustomerDTO;
import com.Nivesh.ECommerceWebsite.email.EmailSendService;
import com.Nivesh.ECommerceWebsite.email.UserActivationRepository;
import com.Nivesh.ECommerceWebsite.exception.UserAlreadyExist;
import com.Nivesh.ECommerceWebsite.repos.CustomerRepository;
import com.Nivesh.ECommerceWebsite.repos.RoleRepository;
import com.Nivesh.ECommerceWebsite.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private UserActivationRepository userActivationRepository;

    @Autowired
    CustomerRepository customerRepository;

    private EmailSendService emailSendService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    private AddressService addressService;

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll().stream()
                .map(o ->customerConvertEntityToDto(o)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO customerConvertEntityToDto(Customer customer) {
        return new CustomerDTO(customer.getContact(),
                userService.userConvertEntityTODto(customer.getUser()));
    }

    @Override
    public Customer customerConvertDtoToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setContact(customerDTO.getContactNumber());
        return customer;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        User user=userService.userConvertDtoToEntity(customerDTO.getUserDTO());

        User userExist=userRepository.findByEmail(user.getEmail());
        if(userExist!=null)
            throw new UserAlreadyExist("USER ALREADY EXIST WITH EMAIL"+userExist.getEmail()+"\n TRY AGAIN");

        Customer customer=customerConvertDtoToEntity(customerDTO);

        Role role = roleRepository.findByAuthority("ROLE_CUSTOMER");
        Set<Role> roleList = new HashSet<>();
        roleList.add(role);
        user.setRoles(roleList);

        if (userService.checkPasswordAndConfirmPassword(user.getPassword(),user.getConfirmPassword())) {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            customer.setUser(user);

            customerRepository.save(customer);

            sendEmail(user);
            return customerDTO;
        }
        throw new RuntimeException("error occurred while creating user");
    }

    @Override
    public String resendToken(User user) {
        if (user.isActive() == false) {
            UserActivation confirmationToken = userActivationRepository.findByUser(user);
            userActivationRepository.deleteConfirmationToken(confirmationToken.getConfirmationToken());
            sendEmail(user);
            return "new email sent";
        } else
            return "Account already active";
    }

    @Override
    public String checkEmail(String email) {
        System.out.println(email);
        User user=userRepository.findByEmail(email);
        if (user!=null)
            return resendToken(user);
        else
            return "email doesnot exist";
    }

    @Override
    public void sendEmail(User user) {

        UserActivation confirmationToken= new UserActivation(user);
        userActivationRepository.save(confirmationToken);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setFrom("test@gmail.com");
        message.setSubject("Complete Registration");
        message.setText("To confirm your account, please click here:"+"http://localhost:9092/register/customer/confirm?token="
                +confirmationToken.getConfirmationToken());
        emailSendService.sendEmail(message);
    }

    @Override
    public String confirmToken(String token) {
        UserActivation confirmationToken=userActivationRepository.findByConfirmationToken(token);
        if(confirmationToken!=null)
        {

            Date expiryDate=confirmationToken.getExpiryDate();
            if(expiryDate.before(new Date()))
            {
                userActivationRepository.deleteConfirmationToken(token);
                sendEmail(confirmationToken.getUser());
                String tokenExpired="TOKEN EXPIRED+\n mail has been sent to your email id with another token link";

                return tokenExpired;
            }
            else
            {
                User user = confirmationToken.getUser();
                System.out.println(user.getUserId());
                User user1 = userRepository.getById(user.getUserId());
                user.setActive(true);
                userRepository.save(user1);
                userActivationRepository.deleteConfirmationToken(token);
                return "confirm";
            }
        }
        else {
            String tokenInvalid="TOKEN INVALID \n CLICK ON THIS LINK:http://localhost:9092/register/customer/resendToken";
            return tokenInvalid;

        }
    }

    @Override
    public Set<AddressDTO> getCustomerAddress(String email) {
        User user=userRepository.findByEmail(email);
        return addressService.addressConvertEntityTODto(user.getAddresses());
    }
}
