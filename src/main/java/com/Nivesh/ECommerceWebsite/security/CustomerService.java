package com.Nivesh.ECommerceWebsite.security;

import com.Nivesh.ECommerceWebsite.Entities.Users.Customer;
import com.Nivesh.ECommerceWebsite.Entities.Users.User;
import com.Nivesh.ECommerceWebsite.Entities.dto.AddressDTO;
import com.Nivesh.ECommerceWebsite.Entities.dto.CustomerDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface CustomerService {

    public List<CustomerDTO> getCustomers();

    CustomerDTO customerConvertEntityToDto(Customer customer);

    Customer customerConvertDtoToEntity(CustomerDTO customerDTO);

    public CustomerDTO saveCustomer(CustomerDTO customerDTO);

    @Transactional
    String resendToken(User user);

    String checkEmail(String email);

    void sendEmail(User user);

    public String confirmToken(String token);

    public Set<AddressDTO> getCustomerAddress(String email);
}
