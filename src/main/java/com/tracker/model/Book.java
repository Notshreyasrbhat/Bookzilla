package com.tracker.model;


import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

// MODEL LAYER: Inherits from Content. 
// Adds specific properties like 'total_pages' to a standard piece of content.
@Entity
@NoArgsConstructor
public abstract class Book extends Content {
    
    @jakarta.persistence.Column(name = "total_pages")
    private Integer totalPages;

    public Book(String title, String author, String description) {
        super(title, author, description);
        this.totalPages = (int)(Math.random() * 500) + 100;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
