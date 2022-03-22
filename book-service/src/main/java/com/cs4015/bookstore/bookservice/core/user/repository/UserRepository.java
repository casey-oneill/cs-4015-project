package com.cs4015.bookstore.bookservice.core.user.repository;

import com.cs4015.bookstore.bookservice.core.user.model.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByUsername(String username);
}
