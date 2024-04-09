package com.logicea.cards.QuerySpecs;

import org.springframework.data.jpa.domain.Specification;

import com.logicea.cards.DTOs.CardByIdFilter;
import com.logicea.cards.Models.Card;

public class CardByIdSpec {

    public static final String CARD_ID = "id";
    public static final String CREATEDBY = "createdBy";

    private CardByIdSpec() {
        // empty
    }

    public static Specification<Card> filterBy(CardByIdFilter carsFilter) {
        return Specification
                .where(hasUser(carsFilter.createdBy()))
                .and(hasId(carsFilter.cardId()));
    }

    private static Specification<Card> hasUser(Integer creator) {
        return ((root, query, cb) -> creator == 0 ? cb.conjunction()
                : cb.equal(root.get(CREATEDBY), creator));
    }

    private static Specification<Card> hasId(Integer cardId) {
        return ((root, query, cb) -> cardId == 0 ? cb.conjunction()
                : cb.equal(root.get(CARD_ID), cardId));
    }
}
