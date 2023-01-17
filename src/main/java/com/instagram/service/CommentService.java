package com.instagram.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.dto.CommentDto;
import com.instagram.model.Comment;
import com.instagram.model.Post;
import com.instagram.model.User;
import com.instagram.repo.CommentRepository;

@Service
public class CommentService {

	private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;

	public Comment submitComment(CommentDto commentDto) {

		User user = userService.checkUsernameAvailable(commentDto.getUser().getUsername());

		Post post = postService.getPost(commentDto.getPost().getPostId());

		Comment comment = new Comment();
		comment.setComment(commentDto.getComment());
		comment.setUser(user);
		comment.setPost(post);

		logger.info("comment submitted successfully !!!");
		return commentRepo.save(comment);

	}

	public List<Comment> getAllComment() {
		return commentRepo.findAll();
	}

	public void deleteComment(int userId) {

		User user = userService.getUser(userId);
		commentRepo.deleteById(user.getUserId());

		logger.info("Deleted comment successfully !!!");
	}
}
