package com.example.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a book entity in the system.
 * This entity is mapped to a database table using JPA annotations.
 */
@Entity
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods.
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor.
@AllArgsConstructor // Lombok annotation to generate an all-arguments constructor.
@Builder // Lombok annotation to provide a builder pattern for creating instances.
public class Book {

    /**
     * Primary key for the Book entity.
     * It is auto-generated using the IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Title of the book.
     * This field is mandatory, validated using @NotBlank.
     */
    @NotBlank(message = "Title is mandatory")
    private String title;

    /**
     * Author of the book.
     * This field is mandatory, validated using @NotBlank.
     */
    @NotBlank(message = "Author is mandatory")
    private String author;

    /**
     * Price of the book.
     * Must be a positive value, validated using @Min.
     */
    @Min(value = 1, message = "Price must be non-negative")
    private double price;
}
