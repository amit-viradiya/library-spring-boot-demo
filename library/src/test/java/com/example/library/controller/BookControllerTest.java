package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @InjectMocks
    BookController bookController;

    @Mock
    BookService bookService;

    // Test for POST /books (Add Book)
    @Test
    public void testAddBook() {
        // Given
        Book book = Book.builder().id(1L).author("Amit").price(10).title("bokked").build();

        // When
        when(bookService.addBook(any())).thenReturn(book);
        ResponseEntity<Book> response = bookController.addBook(book);

        // Then
        verify(bookService, times(1)).addBook(any());
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(book.getId(), response.getBody().getId());
        assertEquals(book.getAuthor(), response.getBody().getAuthor());
        assertEquals(book.getPrice(), response.getBody().getPrice());
        assertEquals(book.getTitle(), response.getBody().getTitle());
    }

    // Test for GET /books (Get All Books)
    @Test
    public void testGetAllBooks() {
        // Given
        Book book = Book.builder().id(1L).author("Amit").price(10).title("bokked").build();
        when(bookService.getAllBooks()).thenReturn(List.of(book));

        // When
        ResponseEntity<List<Book>> response = bookController.getAllBooks();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains(book));
        verify(bookService, times(1)).getAllBooks();
    }

    // Test for GET /books/{id} (Get Book by ID)
    @Test
    public void testGetBookById() {
        // Given
        Book book = Book.builder().id(1L).author("Amit").price(10).title("bokked").build();
        when(bookService.getBookById(1L)).thenReturn(book);

        // When
        ResponseEntity<Book> response = bookController.getBookById(1L);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(book.getId(), response.getBody().getId());
        assertEquals(book.getAuthor(), response.getBody().getAuthor());
        assertEquals(book.getPrice(), response.getBody().getPrice());
        assertEquals(book.getTitle(), response.getBody().getTitle());
        verify(bookService, times(1)).getBookById(1L);
    }

    // Test for PUT /books/{id} (Update Book)
    @Test
    public void testUpdateBook() {
        // Given
        Book existingBook = Book.builder().id(1L).author("Amit").price(10).title("bokked").build();
        Book updatedBook = Book.builder().id(1L).author("Updated Author").price(20).title("Updated Title").build();

        when(bookService.updateBook(1L, updatedBook)).thenReturn(updatedBook);

        // When
        ResponseEntity<Book> response = bookController.updateBook(1L, updatedBook);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedBook.getId(), response.getBody().getId());
        assertEquals(updatedBook.getAuthor(), response.getBody().getAuthor());
        assertEquals(updatedBook.getPrice(), response.getBody().getPrice());
        assertEquals(updatedBook.getTitle(), response.getBody().getTitle());
        verify(bookService, times(1)).updateBook(1L, updatedBook);
    }

    // Test for DELETE /books/{id} (Delete Book)
    @Test
    public void testDeleteBook() {
        // Given
        Long bookId = 1L;
        doNothing().when(bookService).deleteBook(bookId);

        // When
        ResponseEntity<Void> response = bookController.deleteBook(bookId);

        // Then
        verify(bookService, times(1)).deleteBook(bookId);
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}