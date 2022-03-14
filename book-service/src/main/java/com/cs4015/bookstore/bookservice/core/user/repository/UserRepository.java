package com.cs4015.bookstore.bookservice.core.user.repository;

import com.cs4015.bookstore.bookservice.core.user.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
}
