package com.tracker.controller;

import com.tracker.service.ContentService;
import com.tracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// CONTROLLER LAYER: The traffic cop for Admin users.
// Intercepts web requests starting with '/admin', gathers data from Services, and returns the HTML admin pages.
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final ContentService contentService;
    private final com.tracker.service.ReadingProgressService progressService;

    public AdminController(UserService userService, ContentService contentService, com.tracker.service.ReadingProgressService progressService) {
        this.userService = userService;
        this.contentService = contentService;
        this.progressService = progressService;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/users")
    public String users(Model model) {
        java.util.List<com.tracker.model.User> users = userService.getAllUsers();
        java.util.Map<Integer, java.util.List<String>> userBooksMap = new java.util.HashMap<>();
        
        for (com.tracker.model.User user : users) {
            java.util.List<String> books = progressService.getUserProgress(user).stream()
                .map(prog -> prog.getContent().getTitle())
                .collect(java.util.stream.Collectors.toList());
            userBooksMap.put(user.getUserId(), books);
        }
        
        model.addAttribute("users", users);
        model.addAttribute("userBooksMap", userBooksMap);
        return "admin/users";
    }
    
    @GetMapping("/content")
    public String manageContent(Model model) {
        model.addAttribute("contents", contentService.getAllContent());
        return "admin/content";
    }
}
