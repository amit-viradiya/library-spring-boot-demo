package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import com.example.library.service.impl.BookServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Book Management", description = "APIs for managing books")
@Slf4j
public class BookController {

    private final BookService bookService;

    /**
     * Constructor to initialize the BookService.
     *
     * @param bookService the service to manage book operations
     */
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Endpoint to add a new book.
     *
     * @param book the book details to be added
     * @return ResponseEntity containing the added book and HTTP status CREATED
     */
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        log.info("Adding a new book: {}", book);
        Book createdBook = bookService.addBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve all books.
     *
     * @return ResponseEntity containing the list of all books and HTTP status OK
     */
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("Retrieving all books");
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a book by its ID.
     *
     * @param id the ID of the book to retrieve
     * @return ResponseEntity containing the retrieved book and HTTP status OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        log.info("Retrieving book with id: {}", id);
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    /**
     * Endpoint to update an existing book.
     *
     * @param id the ID of the book to update
     * @param updatedBook the updated book details
     * @return ResponseEntity containing the updated book and HTTP status OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book updatedBook) {
        log.info("Updating book with id: {}. New details: {}", id, updatedBook);
        Book book = bookService.updateBook(id, updatedBook);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    /**
     * Endpoint to delete a book by its ID.
     *
     * @param id the ID of the book to delete
     * @return ResponseEntity with HTTP status NO_CONTENT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.info("Deleting book with id: {}", id);
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}