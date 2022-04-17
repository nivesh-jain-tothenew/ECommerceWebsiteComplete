package com.Nivesh.ECommerceWebsite;

import com.Nivesh.ECommerceWebsite.Entities.Users.User;
import com.Nivesh.ECommerceWebsite.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null)
            throw new UsernameNotFoundException("user not found for email " + username);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());
    }
}
