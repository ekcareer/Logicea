package com.logicea.cards.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.logicea.cards.Models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT s FROM User s WHERE s.email = ?1")
    Optional<User> findByEmail(String email);

}
