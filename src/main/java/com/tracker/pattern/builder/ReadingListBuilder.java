package com.tracker.pattern.builder;

import com.tracker.model.Content;
import com.tracker.model.ReadingList;
import com.tracker.model.User;

import java.util.ArrayList;
import java.util.List;

// DESIGN PATTERN: Builder.
// Provides a clean, step-by-step way to construct a complex Reading List object instead of a massive constructor.
public class ReadingListBuilder {
    private String name;
    private User owner;
    private List<Content> contents = new ArrayList<>();

    public ReadingListBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ReadingListBuilder owner(User owner) {
        this.owner = owner;
        return this;
    }

    public ReadingListBuilder addContent(Content content) {
        this.contents.add(content);
        return this;
    }

    public ReadingList build() {
        return new ReadingList(name, owner, contents);
    }
}
