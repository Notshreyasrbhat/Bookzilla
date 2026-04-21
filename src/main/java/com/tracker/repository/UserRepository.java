package com.tracker.repository;

import com.tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// REPOSITORY LAYER: Talks directly to the database.
// Automatically generates SQL queries to register new users or find them by their email during login.
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
