package com.cs4015.bookstore.bookservice.core.book.repository;

import com.cs4015.bookstore.bookservice.core.book.model.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findById(long id);

    Optional<List<BookEntity>> findByUserId(long userId);
}
