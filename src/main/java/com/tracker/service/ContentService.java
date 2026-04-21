package com.tracker.service;

import com.tracker.model.Content;
import com.tracker.model.Genre;
import com.tracker.pattern.factory.ContentFactory;
import com.tracker.repository.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// SERVICE LAYER: The brain for Books/Content.
// Receives commands from the Controller, applies business rules, and tells the Repository to save/delete.
@Service
public class ContentService {
    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public void createContent(Genre genre, String title, String author, String description) {
        Content content = ContentFactory.create(genre, title, author, description);
        java.util.Objects.requireNonNull(content);
        contentRepository.save(content);
    }

    public List<Content> getAllContent() {
        return contentRepository.findAll();
    }
    
    public List<Content> getByGenre(Genre genre) {
        return contentRepository.findByGenre(genre);
    }

    public Optional<Content> getById(int id) {
        return contentRepository.findById(id);
    }

    public void updateContent(int id, String title, String author, String description) {
        contentRepository.findById(id).ifPresent(content -> {
            content.setTitle(title);
            content.setAuthor(author);
            content.setDescription(description);
            contentRepository.save(content);
        });
    }

    public void deleteContent(int id) {
        contentRepository.deleteById(id);
    }
}
