package com.example.library.service.impl;

import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing books.
 */
@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    /**
     * Constructor for BookService.
     * @param bookRepository The repository for book data.
     */
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Adds a new book to the repository.
     * @param book The book to be added.
     * @return The saved book entity.
     */
    @Override
    public Book addBook(Book book) {
        log.debug("Saving new book: {}", book);
        return bookRepository.save(book);
    }

    /**
     * Retrieves all books from the repository.
     * @return A list of all books.
     */
    @Override
    public List<Book> getAllBooks() {
        log.debug("Fetching all books");
        return bookRepository.findAll();
    }

    /**
     * Retrieves a book by its ID.
     * @param id The ID of the book to retrieve.
     * @return The book entity if found.
     * @throws BookNotFoundException If the book is not found.
     */
    @Override
    public Book getBookById(Long id) {
        log.debug("Fetching book by id: {}", id);
        return bookRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Book not found with id: {}", id);
                    return new BookNotFoundException("Book not found with id: " + id);
                });
    }

    /**
     * Updates an existing book with new data.
     * @param id The ID of the book to update.
     * @param updatedBook The updated book details.
     * @return The updated book entity.
     * @throws BookNotFoundException If the book is not found.
     */
    @Override
    public Book updateBook(Long id, Book updatedBook) {
        log.debug("Updating book with id: {}", id);
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Book not found with id: {}", id);
                    return new BookNotFoundException("Book not found with id: " + id);
                });

        // Update book fields
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPrice(updatedBook.getPrice());
        return bookRepository.save(existingBook);
    }

    /**
     * Deletes a book by its ID.
     * @param id The ID of the book to delete.
     * @throws BookNotFoundException If the book is not found.
     */
    @Override
    public void deleteBook(Long id) {
        log.debug("Deleting book with id: {}", id);
        if (!bookRepository.existsById(id)) {
            log.error("Book not found with id: {}", id);
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}
