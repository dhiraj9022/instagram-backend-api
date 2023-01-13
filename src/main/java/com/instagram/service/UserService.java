package com.instagram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

}
