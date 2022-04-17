package com.Nivesh.ECommerceWebsite.Entities.Controllers;

import com.Nivesh.ECommerceWebsite.Entities.Category.Category;
import com.Nivesh.ECommerceWebsite.Entities.Product.Product;
import com.Nivesh.ECommerceWebsite.Entities.Users.Customer;
import com.Nivesh.ECommerceWebsite.Entities.Users.Seller;
import com.Nivesh.ECommerceWebsite.Entities.Users.User;

import com.Nivesh.ECommerceWebsite.email.EmailSendService;
import com.Nivesh.ECommerceWebsite.repos.*;
import com.Nivesh.ECommerceWebsite.security.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.method.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private SecurityService securityService;

    private EmailSendService emailSendService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationContext applicationContext;


    @PostMapping("/registerUser")
    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "login";
    }

    @GetMapping("/login")
    public Object login(@RequestParam String email, @RequestParam String password) {
        boolean result = securityService.login(email, password);
        if (result)
            return "index";
        return new UsernameNotFoundException("bad credentials");
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword(User u) {
        User user = userRepository.findByEmail(u.getEmail());
        return "login";
    }

    @GetMapping("/getSeller")
    public List<Seller> getAllSeller() {
        List<Seller> seller = sellerRepository.findAll();
        return seller;
    }

    @GetMapping("/getCustomer")
    public List<Customer> getAllConstumer() {
        List<Customer> seller = customerRepository.findAll();
        return seller;
    }

    @PutMapping("/activateSeller")
    public ResponseEntity<?> activateSeller(@RequestParam("id") Long id) {
        Optional<Seller> user = sellerRepository.findById(id);
        if(!(user.isPresent())){
            return new ResponseEntity<>("Seller Not Found", HttpStatus.NOT_FOUND);
        } else {
            boolean isActive = user.get().getUser().isActive();
            if(isActive){
                return new ResponseEntity<>("Seller Already active", HttpStatus.OK);
            }else {
                user.get().getUser().setActive(true);
                sellerRepository.save(user.get());
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(user.get().getUser().getEmail());
                message.setFrom("test@gmail.com");
                message.setSubject("Account Activation");
                message.setText("YOUR ACCOUNT HAS BEEN ACTIVATED");
                emailSendService.sendEmail(message);
                return new ResponseEntity<>("User Activated Sucessfully",HttpStatus.CREATED);
            }
        }
    }

    @PutMapping("/activateCustomer")
    public ResponseEntity<?> activateCustomer(@RequestParam("id") Long id){
        Optional<Customer> user = customerRepository.findById(id);
        ;
        if(!(user.isPresent())){
            return new ResponseEntity<>("Customer Not Found", HttpStatus.NOT_FOUND);
        } else {
            boolean isActive = user.get().getUser().isActive();
            if(isActive){
                return new ResponseEntity<>("Customer Already active", HttpStatus.OK);
            }else {
                user.get().getUser().setActive(true);
                customerRepository.save(user.get());
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(user.get().getUser().getEmail());
                message.setFrom("test@gmail.com");
                message.setSubject("Account Activation");
                message.setText("YOUR ACCOUNT HAS BEEN ACTIVATED");
                emailSendService.sendEmail(message);
                return new ResponseEntity<>("User Activated Sucessfully",HttpStatus.CREATED);
            }
        }
    }

    @PutMapping("/deactivateSeller")
    public ResponseEntity<?> deactivateSeller(@RequestParam("id") Long id){
        Optional<Seller> user = sellerRepository.findById(id);
        if(!(user.isPresent())){
            return new ResponseEntity<>("Seller Not Found", HttpStatus.NOT_FOUND);
        } else {
            boolean isActive = user.get().getUser().isActive();
            if(!isActive){
                return new ResponseEntity<>("Seller Already Deactived", HttpStatus.OK);
            }else {
                user.get().getUser().setActive(false);
                sellerRepository.save(user.get());
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(user.get().getUser().getEmail());
                message.setFrom("test@gmail.com");
                message.setSubject("Account Deactivation");
                message.setText("YOUR ACCOUNT HAS BEEN DEACTIVATED");
                emailSendService.sendEmail(message);
                return new ResponseEntity<>("User Deactivated Sucessfully",HttpStatus.CREATED);
            }
        }

    }

    @PutMapping("/deactivateCustomer")
    public ResponseEntity<?> deactivateCustomer(@RequestParam("id") Long id){
        Optional<Customer> user = customerRepository.findById(id);
        if(!(user.isPresent())){
            return new ResponseEntity<>("Customer Not Found", HttpStatus.NOT_FOUND);
        } else {
            boolean isActive = user.get().getUser().isActive();
            if(!isActive){
                return new ResponseEntity<>("Customer Already Deactived", HttpStatus.OK);
            }else {
                user.get().getUser().setActive(false);
                customerRepository.save(user.get());
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(user.get().getUser().getEmail());
                message.setFrom("jain.nivesh123@gmail.com");
                message.setSubject("Account Deactivation");
                message.setText("YOUR ACCOUNT HAS BEEN DEACTIVATED");
                emailSendService.sendEmail(message);
                return new ResponseEntity<>("User Deactivated Sucessfully",HttpStatus.CREATED);
            }
        }

    }

    @GetMapping("/products")
    public Product viewProduct(@RequestParam long id) {
        Product product = productRepository.findById(id);
        return product;
    }

    @GetMapping("/viewAllProduct")
    public List<Product> viewAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @PutMapping("/activateProduct")
    public void activateProduct(@RequestParam long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            if (product.isActive() == false) {
                product.setActive(true);
            }
        }
    }

    @PutMapping("/deactivateProduct")
    public void deactivateProduct(@RequestParam long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            if (product.isActive() == true) {
                product.setActive(false);
            }
        }
    }

    @PostMapping("/addCategory")
    public void addCategory(@RequestParam String name) {
        Category category = new Category();
        category.setName(name);

        categoryRepository.save(category);
    }

    @GetMapping("/viewCategory")
    public List<Category> viewCategory(@RequestParam long id) {
        List<Category> category = categoryRepository.findById(id);
        return category;
    }

    @GetMapping("/allCategories")
    public Category viewAllCategory() {
        Category category = (Category) categoryRepository.findAll();
        return category;
    }

    @PutMapping("/updateCategory")
    public void updateCategory(@Valid @RequestBody long id) {
        List<Category> category = categoryRepository.findById(id);
        if (category != null) {
        }
    }
}
