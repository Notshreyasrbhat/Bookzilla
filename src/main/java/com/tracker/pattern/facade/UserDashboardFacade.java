package com.tracker.pattern.facade;

import com.tracker.model.User;
import com.tracker.service.ContentService;
import com.tracker.service.ReadingListService;
import com.tracker.service.ReadingProgressService;
import com.tracker.service.UserService;
import org.springframework.stereotype.Component;

/**
 * Facade Pattern
 *
 * UserDashboardFacade provides a single, simplified entry point for the
 * DashboardController to gather all data needed for the user dashboard view.
 *
 * Without the Facade, the controller would need to:
 *   1. Call UserService to get the current user.
 *   2. Call ReadingListService to get the user's reading lists.
 *   3. Call ReadingProgressService to get the user's reading progress.
 *   4. Call ContentService to get all available content.
 *
 * The Facade hides this complex subsystem interaction and exposes a single
 * clean method: getDashboardData(username).
 *
 * Subsystems (services) used:
 * - {@link UserService}
 * - {@link ReadingListService}
 * - {@link ReadingProgressService}
 * - {@link ContentService}
 */
// DESIGN PATTERN: Facade.
// Acts as a middleman. Instead of the DashboardController asking 4 different services for data, it asks this Facade, which gathers all the pieces and returns one neat package.
@Component
public class UserDashboardFacade {

    private final UserService userService;
    private final ReadingListService readingListService;
    private final ReadingProgressService readingProgressService;
    private final ContentService contentService;

    public UserDashboardFacade(UserService userService,
                               ReadingListService readingListService,
                               ReadingProgressService readingProgressService,
                               ContentService contentService) {
        this.userService = userService;
        this.readingListService = readingListService;
        this.readingProgressService = readingProgressService;
        this.contentService = contentService;
    }

    /**
     * Aggregates all dashboard data for a given authenticated username
     * into a single {@link DashboardData} object.
     *
     * @param username the email/username of the authenticated user
     * @return a {@link DashboardData} DTO containing all required view data
     */
    public DashboardData getDashboardData(String username) {
        User user = userService.getCurrentUser(username);
        return new DashboardData(
                user,
                readingListService.getUserLists(user),
                readingProgressService.getUserProgress(user),
                contentService.getAllContent()
        );
    }
}
