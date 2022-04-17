package com.Nivesh.ECommerceWebsite.security;

import com.Nivesh.ECommerceWebsite.Entities.UserDetails.Address;
import com.Nivesh.ECommerceWebsite.Entities.UserDetails.Role;
import com.Nivesh.ECommerceWebsite.Entities.Users.Seller;
import com.Nivesh.ECommerceWebsite.Entities.Users.User;
import com.Nivesh.ECommerceWebsite.Entities.dto.SellerDTO;
import com.Nivesh.ECommerceWebsite.email.EmailSendService;
import com.Nivesh.ECommerceWebsite.exception.UserAlreadyExist;
import com.Nivesh.ECommerceWebsite.repos.RoleRepository;
import com.Nivesh.ECommerceWebsite.repos.SellerRepository;
import com.Nivesh.ECommerceWebsite.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SellerServiceImpl implements SellerService{

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    private EmailSendService emailSendService;

    private AddressService addressService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<SellerDTO> getSellers() {
        return sellerRepository.findAll().stream()
                .map(o -> sellerConvertEntityToDto(o)).collect(Collectors.toList());
    }


    @Override
    public SellerDTO sellerConvertEntityToDto(Seller seller) {
        return new SellerDTO(
                seller.getGst(),
                seller.getCompanyContact(),
                seller.getCompanyName(),
                userService.userConvertEntityTODto(seller.getUser()),
                addressService.addressConvertEntityTODto(seller.getUser().getAddresses()));
    }

    @Override
    public Seller sellerConvertDtoToEntity(SellerDTO sellerDTO) {
        Seller seller = new Seller();
        seller.setCompanyContact(sellerDTO.getCompanyContact());
        seller.setCompanyName(sellerDTO.getCompanyName());
        seller.setGst(sellerDTO.getGst());
        return seller;
    }

    @Override
    public SellerDTO saveSeller(SellerDTO sellerDTO) {
        User user = userService.userConvertDtoToEntity(sellerDTO.getUserDTO());

        User userExist = userRepository.findByEmail(user.getEmail());
        if (userExist != null)
            throw new UserAlreadyExist("USER ALREADY EXIST WITH EMAIL" + userExist.getEmail() + "\n TRY AGAIN");

        Set<Address> addresses=sellerDTO.getAddressDTO().
                stream().map(s->addressService.addressConvertDtoToEntity(s)).collect(Collectors.toSet());

        user.setAddresses(addresses);

        Seller Seller = sellerConvertDtoToEntity(sellerDTO);

        Role role = roleRepository.findByAuthority("Seller");
        Set<Role> roleList = new HashSet<>();
        roleList.add(role);
        user.setRoles(roleList);

        if (userService.checkPasswordAndConfirmPassword(user.getPassword(),user.getConfirmPassword())) {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Seller.setUser(user);
            sellerRepository.save(Seller);

            sendEmail(user);
            return sellerDTO;

        }
        throw new RuntimeException("error occured while creating user");
    }

    @Override
    public void sendEmail(User user) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setFrom("test@gmail.com");
        message.setSubject("Registration Complete");
        message.setText("YOUR ACCOUNT HAS BEEN CREATED \n WAIT FOR APPROVAL");
        emailSendService.sendEmail(message);
    }
}
/*
 this.gst = gst;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyContact = companyContact;
 */