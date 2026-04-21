package com.tracker.pattern.prototype;

import com.tracker.model.ReadingProgress;
import com.tracker.model.ReadingStatus;

// DESIGN PATTERN: Prototype.
// Holds a default master copy (0% progress) and clones it for new tracking requests instead of creating new ones.
public class ProgressTemplate {
    private static final ReadingProgress TEMPLATE = new ReadingProgress(ReadingStatus.NOT_STARTED, 0f);

    public static ReadingProgress cloneDefault() {
        try {
            return (ReadingProgress) TEMPLATE.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error cloning progress template", e);
        }
    }
}
