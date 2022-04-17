package com.Nivesh.ECommerceWebsite;

import com.Nivesh.ECommerceWebsite.Entities.UserDetails.Address;
import com.Nivesh.ECommerceWebsite.Entities.UserDetails.Role;
import com.Nivesh.ECommerceWebsite.Entities.Users.Customer;
import com.Nivesh.ECommerceWebsite.Entities.Users.Seller;
import com.Nivesh.ECommerceWebsite.Entities.Users.User;
import com.Nivesh.ECommerceWebsite.repos.CustomerRepository;
import com.Nivesh.ECommerceWebsite.repos.RoleRepository;
import com.Nivesh.ECommerceWebsite.repos.SellerRepository;
import com.Nivesh.ECommerceWebsite.repos.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class ECommerceWebsiteApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	RoleRepository roleRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateUser()
	{
		User user = new User();
		user.setEmail("jainnivesh240@gmail.com");
		user.setFirstName("parth");
		user.setMiddleName("");
		user.setLastName("solanki");
		user.setPassword("sudhajain123@NJ4");
		user.setDeleted(false);
		user.setActive(true);
		user.setExpired(false);
		user.setLocked(false);
		user.setINVALID_ATTEMPT_COUNT(0);

		Address address = new Address();
		address.setAddressLine("304, Shri Managl Nagar");
		address.setCity("Indore");
		address.setCountry("India");
		address.setLabel("office");
		address.setState("Madhya Pradesh");
		address.setZipCode(452001);
		Set<Address> addressList = new HashSet<>();
		addressList.add(address);

		//.setAddresses(addressList);
		userRepository.save(user);
	}

	@Test
	public void testCreateSeller()
	{
		Seller seller = new Seller();
		seller.setGst("AKEO345KWS00");
		seller.setCompanyContact(84983949393L);
		seller.setCompanyName("bike Company");

		sellerRepository.save(seller);
	}

	@Test
	public void testCreateCustomer()
	{
		Customer customer =  new Customer();
		customer.setContact(6265346324L);

		Address address = new Address();
		address.setAddressLine("305, Anand vihar");
		address.setCity("kota");
		address.setCountry("India");
		address.setLabel("home");
		address.setState("Rajhasthan");
		address.setZipCode(341220);
		Set<Address> addressList = new HashSet<>();
		addressList.add(address);
		customer.setAddresses(addressList);

		customerRepository.save(customer);
	}

	@Test
	public void testAddRoles()
	{
		Role role = new Role();
		role.setId(3L);
		role.setAuthority("ROLE_CUSTOMER");

		roleRepository.save(role);
	}
}
