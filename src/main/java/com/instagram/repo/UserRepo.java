package com.instagram.repo;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.instagram.dto.UserDto;
import com.instagram.dto.UserLoginResp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.instagram.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String authEmail);

	Optional<User> findByEmailOrUsername(String email, String username);

	@Query("select new com.instagram.dto.UserLoginResp(u.username, u.email) from User u where u.email = :authEmail")
	Optional<UserLoginResp> findDtoByEmail(String authEmail);

	@Query("select u.username, u.fullName, u.status from User u where u.username =:username")
	List<User> findAllByAuthUser(String username);
}
