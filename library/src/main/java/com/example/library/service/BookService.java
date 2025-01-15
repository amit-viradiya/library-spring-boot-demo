package com.example.library.service;

import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Book;

import java.util.List;

/**
 * Interface for book service operations.
 */
public interface BookService {

    /**
     * Adds a new book to the repository.
     * @param book The book to be added.
     * @return The saved book entity.
     */
    Book addBook(Book book);

    /**
     * Retrieves all books from the repository.
     * @return A list of all books.
     */
    List<Book> getAllBooks();

    /**
     * Retrieves a book by its ID.
     * @param id The ID of the book to retrieve.
     * @return The book entity if found.
     * @throws BookNotFoundException If the book is not found.
     */
    Book getBookById(Long id) throws BookNotFoundException;

    /**
     * Updates an existing book with new data.
     * @param id The ID of the book to update.
     * @param updatedBook The updated book details.
     * @return The updated book entity.
     * @throws BookNotFoundException If the book is not found.
     */
    Book updateBook(Long id, Book updatedBook) throws BookNotFoundException;

    /**
     * Deletes a book by its ID.
     * @param id The ID of the book to delete.
     * @throws BookNotFoundException If the book is not found.
     */
    void deleteBook(Long id) throws BookNotFoundException;
}
