package com.instagram.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.instagram.model.Post;
import com.instagram.model.User;

import lombok.Data;

@Data
public class CommentDto {

	@NotNull
	@NotBlank(message = "comment can't be blank")
	private String comment;

	private User user;

	private Post post;
}
