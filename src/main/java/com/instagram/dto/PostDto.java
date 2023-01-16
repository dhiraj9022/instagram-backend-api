package com.instagram.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.instagram.model.User;

import lombok.Data;

@Data
public class PostDto {

	private String location;
	private int likeCount;

	@NotNull
	@NotBlank(message = "caption can't be blank")
	private String caption;

//	@NotNull
//	@NotBlank(message = "Post image required")
//	private String postImage;

	private User username;

	// private List<Comment> comments = new ArrayList<>();

}
