package com.instagram.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instagram.model.Comment;
import com.instagram.model.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> findAllByPost(Post post);
}
