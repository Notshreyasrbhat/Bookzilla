package com.tracker.service;

import com.tracker.model.Content;
import com.tracker.model.ReadingProgress;
import com.tracker.model.ReadingStatus;
import com.tracker.model.User;
import com.tracker.pattern.prototype.ProgressTemplate;
import com.tracker.repository.ReadingProgressRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReadingProgressService {
    private final ReadingProgressRepository progressRepository;

    public ReadingProgressService(ReadingProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public void startTracking(User user, Content content) {
        // Check if already tracking
        ReadingProgress existing = progressRepository.findByUserUserIdAndContentContentId(user.getUserId(), content.getContentId());
        if (existing != null) {
            return;
        }

        // Use Prototype pattern to clone a default state
        ReadingProgress progress = ProgressTemplate.cloneDefault();
        progress.setUser(user);
        progress.setContent(content);
        
        progressRepository.save(progress);
    }

    public List<ReadingProgress> getUserProgress(User user) {
        return progressRepository.findByUserUserId(user.getUserId());
    }

    public void updateProgress(int progressId, Float percentage, Integer pagesRead) {
        progressRepository.findById(progressId).ifPresent(progress -> {
            Content content = progress.getContent();
            
            if (pagesRead != null) {
                // Safely unproxy or fetch totalPages. Since it's a Book, we can unproxy it.
                com.tracker.model.Book book = (com.tracker.model.Book) org.hibernate.Hibernate.unproxy(content);
                progress.setPagesRead(pagesRead);
                if (book.getTotalPages() != null && book.getTotalPages() > 0) {
                    float newPerc = ((float) pagesRead / book.getTotalPages()) * 100;
                    progress.setPercentage(newPerc);
                }
            } else if (percentage != null) {
                progress.setPercentage(percentage);
            }

            if (progress.getPercentage() >= 100.0f) {
                progress.setStatus(ReadingStatus.COMPLETED);
                progress.setPercentage(100.0f);
                com.tracker.model.Book book = (com.tracker.model.Book) org.hibernate.Hibernate.unproxy(content);
                progress.setPagesRead(book.getTotalPages());
            } else if (progress.getPercentage() > 0) {
                progress.setStatus(ReadingStatus.IN_PROGRESS);
            } else {
                progress.setStatus(ReadingStatus.NOT_STARTED);
            }
            progressRepository.save(progress);
        });
    }

    public void markCompleted(int progressId) {
        updateProgress(progressId, 100.0f, null);
    }

    public void deleteProgress(int progressId) {
        progressRepository.deleteById(progressId);
    }
}
