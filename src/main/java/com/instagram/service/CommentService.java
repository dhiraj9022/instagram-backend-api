package com.instagram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.dto.CommentDto;
import com.instagram.model.Comment;
import com.instagram.model.User;
import com.instagram.repo.CommentRepository;

@Service
public class CommentService {

	private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private UserService userService;

	public Comment submitComment(CommentDto commentDto) {

		Comment comment = new Comment();
		comment.setComment(commentDto.getComment());
		comment.setUser(commentDto.getUsername());
		comment.setPost(commentDto.getPostId());

		logger.info("comment submitted successfully !!!");
		return commentRepo.save(comment);

	}

//	@Transactional
//	public List<Comment> getAllComment() {
//		Post post = new Post();
//		List<Comment> comments = commentRepo.findAllByPost(post);
//		for (Comment comment : comments) {
//			comment.setUser(userService.getUser(comment.getUser().getUserId()));
//		}
//		return comments;
//	}

	public void deleteComment(int userId) {

		User user = userService.getUser(userId);
		commentRepo.deleteById(user.getUserId());

		logger.info("Deleted comment successfully !!!");
	}
}
