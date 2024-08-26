package com.instagram.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;

	@Column(name = "location")
	private String location;

	@Column(name = "post_image")
	private String postImage;

	@Column(name = "caption")
	private String caption;

	@Column(name = "like_count")
	private int likeCount;

	@JsonIgnore
	@ManyToOne
	private User user;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
	private List<Comment> comments = new ArrayList<>();
}
