package com.logicea.cards.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.logicea.cards.Models.Card;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public interface CardRepository extends JpaRepository<Card, Integer>, JpaSpecificationExecutor<Card> {

    Card findByName(String name);

    Page<Card> findAll(@Nullable Specification<Card> spec, @Nonnull Pageable pageable);

    Card findByIdAndCreatedBy(Integer id, Integer createdBy);

    boolean existsByIdAndCreatedBy(Integer id, Integer createdBy);

    Card findByNameAndCreatedBy(String name, Integer currentUser);

}
