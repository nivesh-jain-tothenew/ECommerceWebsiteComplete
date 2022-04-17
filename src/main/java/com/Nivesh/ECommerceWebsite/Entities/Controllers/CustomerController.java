package com.Nivesh.ECommerceWebsite.Entities.Controllers;

import com.Nivesh.ECommerceWebsite.Entities.Category.Category;
import com.Nivesh.ECommerceWebsite.Entities.Product.Product;
import com.Nivesh.ECommerceWebsite.Entities.UserDetails.Address;
import com.Nivesh.ECommerceWebsite.Entities.Users.Customer;
import com.Nivesh.ECommerceWebsite.repos.AddressRepository;
import com.Nivesh.ECommerceWebsite.repos.CategoryRepository;
import com.Nivesh.ECommerceWebsite.repos.CustomerRepository;
import com.Nivesh.ECommerceWebsite.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registerCustomer")
    public String registerCustomer(Customer customer)
    {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
        return "login";
    }

    @GetMapping("/getCustomerDetails")
    public Customer getCustomerDetails(Customer customer)
    {
        customer.getUser().getUserId();
        customer.getUser().getFirstName();
        customer.getUser().getLastName();
        customer.getUser().isActive();
        customer.getContact();
        return customer;
    }

    @GetMapping("/getAllAddresses")
    public Customer getAllAddresses(Customer customer)
    {
        customer.getAddresses();
        return customer;
    }

    @PutMapping("/updateCustomerProfile")
    public void updateCustomerProfile(Customer customer)
    {
        customer.getUser().setFirstName(customer.getUser().getFirstName());
        customer.getUser().setLastName(customer.getUser().getLastName());
        customer.setContact(customer.getContact());
    }

    @PatchMapping("/updateCustomerPassword")
    public void updateCustomerPassword(@RequestParam String password)
    {
        Customer customer = new Customer();
        customer.getUser().setPassword(password);
        customer.getUser().setConfirmPassword(password);

        customerRepository.save(customer);
    }

    @PatchMapping("/updateAddress")
    public void updateAddress(@RequestBody String addressLine,  String city, String country, Integer zipCode, String label)
    {
        Customer customer = new Customer();
        Address address = new Address();

        address.setCity(city);
        address.setCountry(country);
        address.setZipCode(zipCode);
        address.setAddressLine(addressLine);
        address.setLabel(label);
        Set<Address> addressList = new HashSet<>();
        addressList.add(address);

        customer.setAddresses(addressList);
        customerRepository.save(customer);
    }

    @DeleteMapping("/deleteAddress/{id}")
    public void deleteAddress(@RequestParam Long id)
    {
        //Address address = addressRepository.findById(id);
    }

    @GetMapping("/Products/{id}")
    public Product viewProduct(@RequestParam long id)
    {
        Product product = productRepository.findById(id);
        return product;
    }

    @GetMapping("/viewAllProduct/{id}")
    public List<Category> viewAllProducts(@RequestParam long id)
    {
        List<Category> product = categoryRepository.findById(id);
        return product;
    }

    @GetMapping("/findLikeProduct/{id}")
    public List<Product> findLikeProduct(@RequestParam String name)
    {
        List<Product> products = productRepository.findByNameContaining(name);
        return products;
    }

    @GetMapping("/categories")
    public Category viewAllCategory()
    {
        Category category = (Category) categoryRepository.findAll();
        return category;
    }
}
/*
"Id
First Name
Last Name
Is_Active
Contact
Image"

"access token
Address
City
Status
Country
Zip Code
Label"

 */