package com.instagram.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.instagram.dto.*;
import com.instagram.service.utils.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.Enum.Status;
import com.instagram.exception.NotFoundException;
import com.instagram.model.User;
import com.instagram.repo.UserRepo;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AuthUtil authUtil;

	public UserLoginResp getAuthUser() {
		Optional<UserLoginResp> userDto = userRepo.findDtoByEmail(authUtil.getAuthEmail());
		if (!userDto.isPresent()) throw new NotFoundException("user not found");
		return userDto.get();
	}

	public User AddUser(@Valid UserDto userDto) {

		User user = new User();

		user.setUsername(userDto.getUsername());
		user.setFullName(userDto.getFullName());
		user.setBio(userDto.getBio());
		user.setStatus(Status.ONLINE);
		logger.info("User successfully added !!!");

		return userRepo.save(user);

	}

	public User uploadAvatar(@Valid AvatarDto avatarDto) {
		User user = new User();
		user.setAvatar(avatarDto.getAvatar());
		return userRepo.save(user);
	}

	public User updateAvatar(AvatarDto avatarDto, int avatarId) {

		User updateAvatar = new User();

		if (!userRepo.existsById(avatarId)) {
			throw new NotFoundException("Avatar id not matches");
		}
		updateAvatar.setAvatar(avatarDto.getAvatar());
		return userRepo.save(updateAvatar);
	}

	public User updateUsername(UserDto userDto, String username) {

		User updateUsername = getUserByUsername(username);
		updateUsername.setUsername(userDto.getUsername());

		logger.info("Username updated successfully !!!");

		return userRepo.save(updateUsername);
	}

	public String enableStatus(String username) {
		User user = getUserByUsername(username);

		if (user.getStatus() == Status.ONLINE) {
			user.setStatus(Status.OFFLINE);
		} else {
			user.setStatus(Status.ONLINE);
		}

		return "Ok";
	}

	public User updateUserInfo(@Valid UserInfoDto infoDto, String username) {

		User updateUser = getUserByUsername(username);
		updateUser.setFullName(infoDto.getFullName());
		updateUser.setBio(infoDto.getBio());
		logger.info("User info updated successfully !!!");

		return userRepo.save(updateUser);
	}

	public User getUserByEmail(String email) {
		Optional<User> userOpt = userRepo.findByEmail(authUtil.getAuthEmail());
		if (!userOpt.get().getEmail().equals(email)) {
			throw new NotFoundException("User " + email + " not found");
		}
		return userOpt.get();
	}

	public User getUserByUsername(String username) {
		Optional<User> userOpt = userRepo.findByUsername(authUtil.getAuthUser().getUsername());
		if (!userOpt.get().getUsername().equals(username)) {
			throw new NotFoundException("User " + username + " not found");
		}
		return userOpt.get();
	}

	public User checkUsernameAvailable(String username) {
		return getUserByUsername(username);
	}

	public List<UserDto> displayAllUser() {
		List<UserDto> userDtos = new ArrayList<>();

		userRepo.findAllByAuthUser(getAuthUser().getUsername()).forEach(u -> {
			UserDto userDto = new UserDto();
			userDto.setFullName(u.getFullName());
			userDto.setUsername(u.getUsername());
			userDto.setStatus(u.getStatus());

			List<PostDto> postDtos = new ArrayList<>();

			u.getPosts().forEach(p -> {
				PostDto postDto = new PostDto();
				postDto.setCaption(p.getCaption());
				postDto.setLikeCount(p.getLikeCount());

//				List<CommentDto> commentDtos = new ArrayList<>();
//				p.getComments().forEach(c -> {
//
//					CommentDto commentDto = new CommentDto();
//					commentDto.setComment(c.getComment());
//
//					commentDtos.add(commentDto);
//				});

				postDtos.add(postDto);

			});

			userDto.setPosts(u.getPosts());

			userDtos.add(userDto);
		});
		return userDtos;
	}

	public void deleteUserPermanent(String username) {
		User user = getUserByUsername(username);
		userRepo.delete(user);
		logger.info("User deleted successfully !!!");
	}

}
