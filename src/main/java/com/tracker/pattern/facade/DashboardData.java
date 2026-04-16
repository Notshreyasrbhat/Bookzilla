package com.tracker.pattern.facade;

import com.tracker.model.Content;
import com.tracker.model.ReadingList;
import com.tracker.model.ReadingProgress;
import com.tracker.model.User;

import java.util.List;

/**
 * Facade Pattern – Data Transfer Object
 *
 * Holds all the aggregated data needed to render the user dashboard.
 * This is produced by the {@link UserDashboardFacade} in a single call,
 * hiding the complexity of fetching from multiple services.
 */
public class DashboardData {

    private final User user;
    private final List<ReadingList> readingLists;
    private final List<ReadingProgress> progressList;
    private final List<Content> allContent;

    public DashboardData(User user,
                         List<ReadingList> readingLists,
                         List<ReadingProgress> progressList,
                         List<Content> allContent) {
        this.user = user;
        this.readingLists = readingLists;
        this.progressList = progressList;
        this.allContent = allContent;
    }

    public User getUser() {
        return user;
    }

    public List<ReadingList> getReadingLists() {
        return readingLists;
    }

    public List<ReadingProgress> getProgressList() {
        return progressList;
    }

    public List<Content> getAllContent() {
        return allContent;
    }
}
