package com.instagram.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.dto.CommentDto;
import com.instagram.model.Comment;
import com.instagram.service.CommentService;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping
	public ResponseEntity<Comment> submitComment(@Valid @RequestBody CommentDto commentDto) {
		return ResponseEntity.ok(commentService.submitComment(commentDto));
	}

	@GetMapping
	public ResponseEntity<List<Comment>> getAllComment() {
		return ResponseEntity.ok(commentService.getAllComment());
	}

	@DeleteMapping("/user/{username}")
	public ResponseEntity<Comment> deleteComment(@PathVariable(name = "username") String username) {
		commentService.deleteComment(username);
		return ResponseEntity.noContent().build();
	}
}
