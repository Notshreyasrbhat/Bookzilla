package com.tracker.repository;

import com.tracker.model.Content;
import com.tracker.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// REPOSITORY LAYER: Talks directly to the database.
// This interface automatically generates SQL queries to find, save, or delete Books/Content.
@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
    List<Content> findByGenre(Genre genre);
    List<Content> findByTitleContainingIgnoreCase(String keyword);
}
