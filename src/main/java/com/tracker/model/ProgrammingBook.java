package com.tracker.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PROGRAMMING")
@NoArgsConstructor
public class ProgrammingBook extends Book {
    public ProgrammingBook(String title, String author, String description) {
        super(title, author, description);
    }
}
