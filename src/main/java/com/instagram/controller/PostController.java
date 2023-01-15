package com.instagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.service.PostService;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

	@Autowired
	private PostService postService;

}
