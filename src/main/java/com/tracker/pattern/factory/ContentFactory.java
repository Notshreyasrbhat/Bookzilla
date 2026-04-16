package com.tracker.pattern.factory;

import com.tracker.model.Content;
import com.tracker.model.Genre;
import com.tracker.model.FictionBook;
import com.tracker.model.ProgrammingBook;
import com.tracker.model.ProductivityBook;

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
