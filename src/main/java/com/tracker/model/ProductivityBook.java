package com.tracker.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PRODUCTIVITY")
@NoArgsConstructor
public class ProductivityBook extends Book {
    public ProductivityBook(String title, String author, String description) {
        super(title, author, description);
    }
}
