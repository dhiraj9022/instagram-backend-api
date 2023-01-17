package com.instagram.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instagram.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
