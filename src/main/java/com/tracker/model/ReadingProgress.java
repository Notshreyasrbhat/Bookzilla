package com.tracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ReadingProgress implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int progressId;

    @Enumerated(EnumType.STRING)
    private ReadingStatus status;

    private float percentage;

    private Integer pagesRead = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    public ReadingProgress(ReadingStatus status, float percentage) {
        this.status = status;
        this.percentage = percentage;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
