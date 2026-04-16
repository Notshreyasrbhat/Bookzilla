package com.tracker.repository;

import com.tracker.model.ReadingProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingProgressRepository extends JpaRepository<ReadingProgress, Integer> {
    List<ReadingProgress> findByUserUserId(int userId);
    ReadingProgress findByUserUserIdAndContentContentId(int userId, int contentId);
}
