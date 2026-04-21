package com.tracker.controller;

import com.tracker.model.Content;
import com.tracker.model.User;
import com.tracker.service.ContentService;
import com.tracker.service.ReadingProgressService;
import com.tracker.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// CONTROLLER LAYER: The traffic cop for Tracking.
// Catches when a user clicks "Start Tracking" or "Update Pages Read" and passes that info to the Service layer.
@Controller
@RequestMapping("/progress")
public class ReadingProgressController {
    
    private final ReadingProgressService progressService;
    private final ContentService contentService;
    private final UserService userService;

    public ReadingProgressController(ReadingProgressService progressService, ContentService contentService, UserService userService) {
        this.progressService = progressService;
        this.contentService = contentService;
        this.userService = userService;
    }

    @GetMapping("/start/{contentId}")
    public String startTracking(@PathVariable int contentId, Authentication auth, @RequestParam(required = false) String redirectUrl) {
        User user = userService.getCurrentUser(auth.getName());
        Content content = contentService.getById(contentId).orElseThrow();
        progressService.startTracking(user, content);
        return redirectUrl != null ? "redirect:" + redirectUrl : "redirect:/dashboard";
    }

    @PostMapping("/start")
    public String startTrackingPost(@RequestParam int contentId, Authentication auth, @RequestParam(required = false) String redirectUrl) {
        User user = userService.getCurrentUser(auth.getName());
        Content content = contentService.getById(contentId).orElseThrow();
        progressService.startTracking(user, content);
        return redirectUrl != null ? "redirect:" + redirectUrl : "redirect:/dashboard";
    }

    @PostMapping("/update/{id}")
    public String updateProgress(@PathVariable int id, 
                                 @RequestParam(required = false) Float percentage,
                                 @RequestParam(required = false) Integer pagesRead,
                                 @RequestParam(required = false) String redirectUrl) {
        progressService.updateProgress(id, percentage, pagesRead);
        return redirectUrl != null ? "redirect:" + redirectUrl : "redirect:/dashboard";
    }

    @GetMapping("/delete/{id}")
    public String deleteProgress(@PathVariable int id) {
        progressService.deleteProgress(id);
        return "redirect:/dashboard";
    }
}
