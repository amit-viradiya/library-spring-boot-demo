package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
        Book addedBook = bookController.addBook(book);

        // Then
        verify(bookService, times(1)).addBook(any());
        assertNotNull(addedBook);
        assertEquals(book.getId(), addedBook.getId());
        assertEquals(book.getAuthor(), addedBook.getAuthor());
        assertEquals(book.getPrice(), addedBook.getPrice());
        assertEquals(book.getTitle(), addedBook.getTitle());
    }

    // Test for GET /books (Get All Books)
    @Test
    public void testGetAllBooks() {
        // Given
        Book book = Book.builder().id(1L).author("Amit").price(10).title("bokked").build();
        when(bookService.getAllBooks()).thenReturn(List.of(book));

        // When
        List<Book> books = bookController.getAllBooks();

        // Then
        assertNotNull(books);
        assertTrue(books.contains(book));
        verify(bookService, times(1)).getAllBooks();
    }

    // Test for GET /books/{id} (Get Book by ID)
    @Test
    public void testGetBookById() {
        // Given
        Book book = Book.builder().id(1L).author("Amit").price(10).title("bokked").build();
        when(bookService.getBookById(1L)).thenReturn(book);

        // When
        Book returnedBook = bookController.getBookById(1L);

        // Then
        assertNotNull(returnedBook);
        assertEquals(book.getId(), returnedBook.getId());
        assertEquals(book.getAuthor(), returnedBook.getAuthor());
        assertEquals(book.getPrice(), returnedBook.getPrice());
        assertEquals(book.getTitle(), returnedBook.getTitle());
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
        Book returnedBook = bookController.updateBook(1L, updatedBook);

        // Then
        assertNotNull(returnedBook);
        assertEquals(updatedBook.getId(), returnedBook.getId());
        assertEquals(updatedBook.getAuthor(), returnedBook.getAuthor());
        assertEquals(updatedBook.getPrice(), returnedBook.getPrice());
        assertEquals(updatedBook.getTitle(), returnedBook.getTitle());
        verify(bookService, times(1)).updateBook(1L, updatedBook);
    }

    // Test for DELETE /books/{id} (Delete Book)
    @Test
    public void testDeleteBook() {
        // Given
        Long bookId = 1L;
        doNothing().when(bookService).deleteBook(bookId);

        // When
        bookController.deleteBook(bookId);

        // Then
        verify(bookService, times(1)).deleteBook(bookId);
    }
}