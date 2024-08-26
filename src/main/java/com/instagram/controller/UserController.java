package com.instagram.controller;

import java.util.List;

import javax.validation.Valid;

import com.instagram.dto.UserLoginResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.dto.UserDto;
import com.instagram.dto.UserInfoDto;
import com.instagram.model.User;
import com.instagram.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/logged")
	public UserLoginResp getUser(){
		return userService.getAuthUser();
	}

	@GetMapping("/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable(name = "username") String username) {
		return ResponseEntity.ok(userService.getUserByUsername(username));
	}

	@GetMapping("/checkUsername/{username}")
	public ResponseEntity<User> checkUsername(@PathVariable(name = "username") String username) {
		return ResponseEntity.ok(userService.checkUsernameAvailable(username));
	}

	@PostMapping("/{username}")
	public ResponseEntity<String> checkStatus(@PathVariable(name = "username") String username) {
		return ResponseEntity.ok(userService.enableStatus(username));
	}

	@PutMapping("/username/{username}")
	public ResponseEntity<User> updateUsername(@Valid @RequestBody UserDto userDto,
			@PathVariable(name = "username") String username) {
		return ResponseEntity.ok(userService.updateUsername(userDto, username));
	}

	@PutMapping("/{username}")
	public ResponseEntity<User> updateUser(@Valid @RequestBody UserInfoDto userInfoDto,
			@PathVariable(name = "username") String username) {
		return ResponseEntity.ok(userService.updateUserInfo(userInfoDto, username));
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<User> deleteUserPermanent(@PathVariable(name = "username") String username) {

		userService.deleteUserPermanent(username);
		return ResponseEntity.noContent().build();
	}

}
