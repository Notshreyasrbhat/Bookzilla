package com.tracker.pattern.factory;

import com.tracker.model.Content;
import com.tracker.model.Genre;
import com.tracker.model.FictionBook;
import com.tracker.model.ProgrammingBook;
import com.tracker.model.ProductivityBook;

// DESIGN PATTERN: Factory Method.
// Instead of calling 'new FictionBook()' directly, Services ask this Factory to create the right type of book based on Genre.
public class ContentFactory {
    public static Content create(Genre genre, String title, String author, String description) {
        return switch (genre) {
            case FICTION -> new FictionBook(title, author, description);
            case PROGRAMMING -> new ProgrammingBook(title, author, description);
            case PRODUCTIVITY -> new ProductivityBook(title, author, description);
            default -> new FictionBook(title, author, description);
        };
    }
}
