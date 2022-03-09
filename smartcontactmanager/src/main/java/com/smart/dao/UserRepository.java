package com.smart.dao;

import javax.validation.constraints.Email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smart.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u from user u where u.email=:email")
	public User getUserByUserName(@Param("email") String email);

}
