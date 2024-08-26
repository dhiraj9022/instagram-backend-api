package com.instagram.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.instagram.service.utils.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.dto.PostDto;
import com.instagram.exception.NotFoundException;
import com.instagram.model.Post;
import com.instagram.model.User;
import com.instagram.repo.PostRep;
import com.instagram.repo.UserRepo;

@Service
public class PostService {

	private static final Logger logger = LoggerFactory.getLogger(PostService.class);

	@Autowired
	private PostRep postRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AuthUtil authUtil;

	public Post submitPost(@Valid PostDto postDto) {

		User user = userService.checkUsernameAvailable(authUtil.getAuthUser().getUsername());

		Post post = new Post();
		post.setCaption(postDto.getCaption());
		post.setLocation(postDto.getLocation());
		post.setLikeCount(postDto.getLikeCount());
		post.setUser(user);

		logger.info("Post submitted successfully");
		return postRepo.save(post);
	}

	public List<PostDto> getAllPost() {
		List<PostDto> postDtos = new ArrayList<>();

		postRepo.findAll().forEach(p -> {

			PostDto postDto = new PostDto();
			postDto.setCaption(p.getCaption());
			postDto.setComments(p.getComments());
			postDto.setLikeCount(p.getLikeCount());

			postDtos.add(postDto);
		});
		return postDtos;
	}

	public Post getPost(int postId) {
		Optional<Post> postOpt = postRepo.findById(postId);
		if (!postOpt.isPresent()) {
			throw new NotFoundException("Post " + postId + " is not found");
		}
		return postOpt.get();
	}

	public Post updatePost(@Valid PostDto postDto, int postId) {

		Post updatePost = getPost(postId);

		updatePost.setCaption(postDto.getCaption());
		updatePost.setLocation(postDto.getLocation());
		updatePost.setLikeCount(postDto.getLikeCount());

		logger.info("Post updated submitted successfully");

		return postRepo.save(updatePost);
	}

	public void deletePost(String username) {
		User user = userService.getUserByUsername(username);
		if (user == null){
			logger.error("User not found: " + username);
			return;
		}

		List<Post> posts = user.getPosts();
		if (posts.isEmpty()) {
			logger.info("No posts found for user: " + username);
		} else {
			posts.forEach(post -> {
				logger.info("Deleting post with ID: " + post.getPostId());
				postRepo.delete(post);
			});
		}
		logger.info("Post(s) deleted successfully");
	}

}
