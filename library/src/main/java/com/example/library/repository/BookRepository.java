package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Book} entities.
 *
 * This interface extends JpaRepository, providing standard CRUD operations
 * and additional JPA-related methods for the {@link Book} entity.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // JpaRepository provides methods like save, findById, findAll, deleteById, etc.
}