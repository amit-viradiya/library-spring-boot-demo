package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book addBook(@Valid @RequestBody Book book) {
        log.info("Adding a new book: {}", book);
        return bookService.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        log.info("Retrieving all books");
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        log.info("Retrieving book with id: {}", id);
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @Valid @RequestBody Book updatedBook) {
        log.info("Updating book with id: {}. New details: {}", id, updatedBook);
        return bookService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        log.info("Deleting book with id: {}", id);
        bookService.deleteBook(id);
    }
}