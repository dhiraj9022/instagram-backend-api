package com.instagram.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.instagram.model.Comment;
import com.instagram.model.User;

import lombok.Data;

@Data
public class PostDto {

	@NotNull
	@NotBlank(message = "caption can't be blank")
	private String caption;

	private String location;

	private int likeCount;

	private User user;

	private List<Comment> comments = new ArrayList<>();

}
