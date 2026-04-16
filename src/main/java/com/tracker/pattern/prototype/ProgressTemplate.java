package com.tracker.pattern.prototype;

import com.tracker.model.ReadingProgress;
import com.tracker.model.ReadingStatus;

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
