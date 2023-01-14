package com.instagram.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.instagram.model.User;
import com.instagram.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> addUser(@Valid @RequestBody UserDto userDto) {
		return ResponseEntity.ok(userService.AddUser(userDto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable(name = "id") int userId) {
		return ResponseEntity.ok(userService.getUser(userId));
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		return ResponseEntity.ok(userService.displayAllUser());
	}

	@PostMapping("/{userId}")
	public ResponseEntity<User> checkStatus(@PathVariable int userId) {
		return ResponseEntity.ok(userService.enableStatus(userId));
	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUsername(@Valid @RequestBody UserDto userDto,
			@PathVariable(name = "id") int userId) {
		return ResponseEntity.ok(userService.updateUsername(userDto, userId));
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable(name = "id") int userId) {
		return ResponseEntity.ok(userService.updateUserInfo(userDto, userId));
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<User> deleteUserPermanent(@PathVariable int userId) {

		userService.deleteUserPermanent(userId);
		return ResponseEntity.noContent().build();
	}

}
