package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // fetching user from database
        User user = (User) userRepository.getUserByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user!"+username);
        }

        // Assuming CustomUserDetails is a specific class with a constructor that takes a User parameter
        CustomUserDetails customUserDetails = new CustomUserDetails((com.example.demo.entities.User) user);

        //return customUserDetails;
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                // Add any additional authorities if needed
                // AuthorityUtils.createAuthorityList("ROLE_USER")
                ((UserDetails) user).getAuthorities()
            );
    }
}
