package com.example.library.service;

import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        log.debug("Saving new book: {}", book);
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        log.debug("Fetching all books");
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        log.debug("Fetching book by id: {}", id);
        return bookRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Book not found with id: {}", id);
                    return new BookNotFoundException("Book not found with id: " + id);
                });
    }

    public Book updateBook(Long id, Book updatedBook) {
        log.debug("Updating book with id: {}", id);
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Book not found with id: {}", id);
                    return new BookNotFoundException("Book not found with id: " + id);
                });

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPrice(updatedBook.getPrice());
        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        log.debug("Deleting book with id: {}", id);
        if (!bookRepository.existsById(id)) {
            log.error("Book not found with id: {}", id);
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}
