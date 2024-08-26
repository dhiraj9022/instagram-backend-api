package com.instagram.service;

import com.instagram.exception.NotFoundException;
import com.instagram.model.User;
import com.instagram.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public AuthUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) throw new NotFoundException("user not found");
        return new org.springframework.security.core.userdetails.User(email, user.get().getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
