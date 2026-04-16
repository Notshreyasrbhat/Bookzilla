package com.tracker.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("FICTION")
@NoArgsConstructor
public class FictionBook extends Book {
    public FictionBook(String title, String author, String description) {
        super(title, author, description);
    }
}
