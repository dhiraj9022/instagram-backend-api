package com.instagram.controller;

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

import com.instagram.dto.PostDto;
import com.instagram.model.Post;
import com.instagram.service.PostService;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping
	public ResponseEntity<Post> submitPost(@Valid @RequestBody PostDto postDto) {
		return ResponseEntity.ok(postService.submitPost(postDto));
	}

//	@GetMapping
//	public ResponseEntity<List<Post>> getAllPost() {
//		return ResponseEntity.ok(postService.getAllPost());
//	}

	@GetMapping("/{post_id}")
	public ResponseEntity<Post> getPost(@PathVariable(name = "post_id") int postId) {
		return ResponseEntity.ok(postService.getPost(postId));
	}

	@DeleteMapping("/{post_id}")
	public ResponseEntity<Post> deletePost(@PathVariable(name = "post_id") int postId) {
		postService.deletePost(postId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{post_id}")
	public ResponseEntity<Post> editPost(@Valid @RequestBody PostDto postDto,
			@PathVariable(name = "post_id") int postId) {
		return ResponseEntity.ok(postService.updatePost(postDto, postId));
	}

}
