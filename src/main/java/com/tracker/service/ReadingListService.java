package com.tracker.service;

import com.tracker.model.Content;
import com.tracker.model.ReadingList;
import com.tracker.model.User;
import com.tracker.pattern.builder.ReadingListBuilder;
import com.tracker.repository.ReadingListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// SERVICE LAYER: The brain for Reading Lists.
// Handles the logic for creating playlists of books using the Builder pattern.
@Service
public class ReadingListService {
    private final ReadingListRepository readingListRepository;

    public ReadingListService(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    public ReadingList createList(String name, User owner, List<Content> contents) {
        ReadingListBuilder builder = new ReadingListBuilder()
                .name(name)
                .owner(owner);
        
        if (contents != null) {
            for (Content c : contents) {
                builder.addContent(c);
            }
        }
        
        ReadingList list = builder.build();
        java.util.Objects.requireNonNull(list);
        return readingListRepository.save(list);
    }

    public List<ReadingList> getUserLists(User user) {
        return readingListRepository.findByOwnerUserId(user.getUserId());
    }

    public Optional<ReadingList> getListById(int id) {
        return readingListRepository.findById(id);
    }

    public void addContentToList(int listId, Content content) {
        readingListRepository.findById(listId).ifPresent(list -> {
            if (!list.getContents().contains(content)) {
                list.getContents().add(content);
                readingListRepository.save(list);
            }
        });
    }
    
    public void deleteList(int id) {
        readingListRepository.deleteById(id);
    }
}
