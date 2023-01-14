package com.instagram.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.Enum.Status;
import com.instagram.dto.UserDto;
import com.instagram.exception.NotFoundException;
import com.instagram.model.User;
import com.instagram.repo.UserRepository;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepo;

	public User AddUser(@Valid UserDto userDto) {

		User user = new User();

		user.setUsername(userDto.getUsername());
		user.setFullName(userDto.getFullName());
		user.setBio(userDto.getBio());
		user.setAvatar(userDto.getAvatar());
		user.setComments(userDto.getComments());
		user.setPosts(userDto.getPosts());
		user.setStatus(Status.OFFLINE);

		logger.info("User successfully added !!!");

		return userRepo.save(user);

	}

	public User updateUsername(@Valid UserDto userDto, int userId) {

		User updateUsername = getUser(userId);
		updateUsername.setUsername(userDto.getUsername());

		logger.info("Username updated successfully !!!");

		return userRepo.save(updateUsername);
	}

	public User enableStatus(int userId) {

		User user = getUser(userId);
		user.setStatus(Status.ONLINE);
		return user;
	}

	public User updateUserInfo(@Valid UserDto userDto, int userId) {

		User updateUser = getUser(userId);
		updateUser.setFullName(userDto.getFullName());
		updateUser.setBio(userDto.getBio());
		updateUser.setAvatar(userDto.getAvatar());

		logger.info("User info updated successfully !!!");

		return userRepo.save(updateUser);
	}

	public User getUser(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		if (!userOpt.isPresent()) {
			throw new NotFoundException("User " + userId + " not found");
		}
		return userOpt.get();
	}

	public List<User> displayAllUser() {
		return userRepo.findAll();
	}

}
