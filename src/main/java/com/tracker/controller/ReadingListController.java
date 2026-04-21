package com.tracker.controller;

import com.tracker.model.Content;
import com.tracker.model.User;
import com.tracker.service.ContentService;
import com.tracker.service.ReadingListService;
import com.tracker.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// CONTROLLER LAYER: The traffic cop for Playlists/Reading Lists.
// Handles requests to create a new reading list or add a specific book to an existing list.
@Controller
@RequestMapping("/readinglist")
public class ReadingListController {
    
    private final ReadingListService readingListService;
    private final ContentService contentService;
    private final UserService userService;
    private final com.tracker.service.ReadingProgressService readingProgressService;

    public ReadingListController(ReadingListService readingListService, ContentService contentService, UserService userService, com.tracker.service.ReadingProgressService readingProgressService) {
        this.readingListService = readingListService;
        this.contentService = contentService;
        this.userService = userService;
        this.readingProgressService = readingProgressService;
    }

    @GetMapping
    public String viewLists(Authentication auth, Model model) {
        User user = userService.getCurrentUser(auth.getName());
        model.addAttribute("lists", readingListService.getUserLists(user));
        return "readinglist/list";
    }

    @GetMapping("/new")
    public String showForm() {
        return "readinglist/form";
    }

    @PostMapping("/save")
    public String create(@RequestParam String name, Authentication auth) {
        User user = userService.getCurrentUser(auth.getName());
        readingListService.createList(name, user, null);
        return "redirect:/readinglist";
    }

    @GetMapping("/{id}")
    public String viewList(@PathVariable int id, Authentication auth, Model model) {
        User user = userService.getCurrentUser(auth.getName());
        model.addAttribute("list", readingListService.getListById(id).orElseThrow());
        model.addAttribute("allContent", contentService.getAllContent());
        
        java.util.List<com.tracker.model.ReadingProgress> progressList = readingProgressService.getUserProgress(user);
        java.util.Map<Integer, com.tracker.model.ReadingProgress> progressMap = progressList.stream()
            .collect(java.util.stream.Collectors.toMap(p -> p.getContent().getContentId(), p -> p));
        model.addAttribute("progressMap", progressMap);
        
        return "readinglist/detail";
    }

    @PostMapping("/{id}/add-content")
    public String addContent(@PathVariable int id, @RequestParam int contentId) {
        Content content = contentService.getById(contentId).orElseThrow();
        readingListService.addContentToList(id, content);
        return "redirect:/readinglist/" + id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        readingListService.deleteList(id);
        return "redirect:/readinglist";
    }
}
