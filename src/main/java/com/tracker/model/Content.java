package com.tracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

// MODEL LAYER: The core data structure for any readable material in the app.
// It acts as the parent class for Books, Videos, Articles, etc., mapped to a single database table.
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "genre", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
public abstract class Content implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contentId;

    private String title;
    private String author;
    private String description;

    @Column(name = "genre", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    public Content(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }
}
