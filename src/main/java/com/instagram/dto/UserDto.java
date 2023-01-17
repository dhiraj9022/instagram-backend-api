package com.instagram.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.instagram.Enum.Status;
import com.instagram.model.Comment;
import com.instagram.model.Post;

import lombok.Data;

@Data
public class UserDto {

	@NotNull
	@NotBlank(message = "username can't be blank")
	private String username;

	private String fullName;

	private String bio;

	private Status status;

	private List<Post> posts = new ArrayList<>();

	private List<Comment> comments = new ArrayList<>();

}
