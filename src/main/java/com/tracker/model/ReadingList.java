package com.tracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class ReadingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int listId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToMany
    @JoinTable(
        name = "reading_list_content",
        joinColumns = @JoinColumn(name = "list_id"),
        inverseJoinColumns = @JoinColumn(name = "content_id")
    )
    private List<Content> contents = new ArrayList<>();

    public ReadingList(String name, User owner, List<Content> contents) {
        this.name = name;
        this.owner = owner;
        this.contents = contents != null ? contents : new ArrayList<>();
    }
}
