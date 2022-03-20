package com.cs4015.bookstore.bookservice.core.user.repository;

import com.cs4015.bookstore.bookservice.core.user.model.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	Optional<UserEntity> findById(long id);

    Optional<List<UserEntity>> findByUserId(long userId);
}
