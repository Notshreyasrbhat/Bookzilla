package com.tracker.controller;

import com.tracker.model.Content;
import com.tracker.model.Genre;
import com.tracker.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// CONTROLLER LAYER: The traffic cop for managing the Library.
// Used by admins to add new books, edit existing books, or delete them.
@Controller
@RequestMapping("/content")
public class ContentController {
    
    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public String listAll(@RequestParam(required = false) Genre genre, Model model) {
        if (genre != null) {
            model.addAttribute("contents", contentService.getByGenre(genre));
        } else {
            model.addAttribute("contents", contentService.getAllContent());
        }
        model.addAttribute("genres", Genre.values());
        model.addAttribute("selectedGenre", genre);
        return "content/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("genres", Genre.values());
        return "content/form";
    }

    @PostMapping("/save")
    public String createContent(@RequestParam Genre genre, @RequestParam String title, 
                                @RequestParam String author, @RequestParam String description) {
        contentService.createContent(genre, title, author, description);
        return "redirect:/content";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Content content = contentService.getById(id).orElseThrow();
        model.addAttribute("content", content);
        model.addAttribute("genres", Genre.values());
        return "content/form";
    }

    @PostMapping("/update/{id}")
    public String updateContent(@PathVariable int id, @RequestParam String title, 
                                @RequestParam String author, @RequestParam String description) {
        contentService.updateContent(id, title, author, description);
        return "redirect:/content";
    }

    @GetMapping("/delete/{id}")
    public String deleteContent(@PathVariable int id) {
        contentService.deleteContent(id);
        return "redirect:/content";
    }
}
