package com.Nivesh.ECommerceWebsite.Entities.Controllers;

import com.Nivesh.ECommerceWebsite.Entities.Category.Category;
import com.Nivesh.ECommerceWebsite.Entities.Product.Product;
import com.Nivesh.ECommerceWebsite.Entities.Product.ProductVariation;
import com.Nivesh.ECommerceWebsite.Entities.UserDetails.Address;
import com.Nivesh.ECommerceWebsite.Entities.Users.Seller;
import com.Nivesh.ECommerceWebsite.Entities.Users.User;
import com.Nivesh.ECommerceWebsite.Entities.dto.SellerDTO;
import com.Nivesh.ECommerceWebsite.repos.*;
import com.Nivesh.ECommerceWebsite.security.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
public class SellerController {

    private SellerService sellerService;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductVariationRepository productVariationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/registerSeller")
    public ResponseEntity<Object> registerSeller(@Valid @RequestBody SellerDTO sellerDTO)
    {
        SellerDTO seller = sellerService.saveSeller(sellerDTO);
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }

    @GetMapping("/getSellerDetails")
    public Seller getSellerDetails(@RequestParam Seller seller)
    {
        seller.getUser().getUserId();
        seller.getUser().getFirstName();
        seller.getUser().getLastName();
        seller.getUser().isActive();
        seller.getCompanyContact();
        seller.getCompanyName();
        seller.getGst();
        return seller;
    }

    @PutMapping("/updateSellerProfile")
    public void updateSellerProfile(Seller seller)
    {
        seller.getUser().setFirstName(seller.getUser().getFirstName());
        seller.getUser().setLastName(seller.getUser().getLastName());
        seller.getUser().setActive(seller.getUser().isActive());
        seller.setCompanyContact(seller.getCompanyContact());
        seller.setGst(seller.getGst());

        sellerRepository.save(seller);
    }

    @PatchMapping("/updateSellerPassword")
    public void updateSellerPassword(String password)
    {
        Seller seller = new Seller();
        seller.getUser().setPassword(password);
        seller.getUser().setConfirmPassword(password);

        sellerRepository.save(seller);
    }

    @PostMapping("/addProduct")
    public void addProduct(Product product)
    {
        product.setName("redmi note 10 pro");
        product.setBrand("VIVO");
        product.getCategory().setId(1);
        product.setDescription("Redmi mobile phones");
        product.setCancellable(false);
        product.setIsReturnable(false);

        Category category = new Category();
        category.setId(1);
        category.setName("mobile phones");

        productRepository.save(product);
    }

    @GetMapping("/product/{id}")
    public Product viewProduct(@RequestParam long id)
    {
        Product product = productRepository.findById(id);
        return product;
    }

    @GetMapping("/productVariation/{id}")
    public ProductVariation viewProductVariation(@RequestParam long id)
    {
        ProductVariation productVariation = productVariationRepository.findById(id);
        return productVariation;
    }

    @GetMapping("/viewAllProducts")
    private List<Product> viewAllProducts()
    {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@RequestParam long id)
    {
        Product product = productRepository.findById(id);
        productRepository.delete(product);
    }

    @PutMapping("/product/{id}/{description}/{isCancellable}/{isReturnable}")
    public Product updateProduct(@RequestParam long id, @RequestParam String description, @RequestParam boolean isCancellable, @RequestParam boolean isReturnable)
    {
        Product product = productRepository.findById(id);
        if(product!=null)
        {
            product.setDescription(description);
            product.setCancellable(isCancellable);
            product.setIsReturnable(isReturnable);
        }
        return product;
    }

    @GetMapping("/category")
    public Category viewAllCategory()
    {
        Category category = (Category) categoryRepository.findAll();
        return category;
    }



    /*
    "Description
IS_CANCELLABLE (false)
IS_RETURNABLE (false)"


    @PatchMapping("/updateAddress")
    public void updateAddress(Set<Address> address)
    {
        Seller seller = new Seller();
        seller.setAddresses(address);

        sellerRepository.save(seller);
    }
*/

    /*
    "Id
First Name
Last Name
Is_Active
Company Contact
Company Name
Image
GST

Address:
City
State
Country
Address Line
Zip Code"

seller.getUser().getUserId();
        seller.getUser().getFirstName();
        seller.getUser().getLastName();
        seller.getUser().isActive();
        seller.getCompanyContact();
        seller.getCompanyName();
        seller.getGst();

 "Name
Brand
Category Id"

     */
}
