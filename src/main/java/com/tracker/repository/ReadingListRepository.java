package com.tracker.repository;

import com.tracker.model.ReadingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingListRepository extends JpaRepository<ReadingList, Integer> {
    List<ReadingList> findByOwnerUserId(int userId);
}
