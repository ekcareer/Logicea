package com.logicea.cards.QuerySpecs;

import org.springframework.data.jpa.domain.Specification;

import com.logicea.cards.DTOs.CardFilter;
import com.logicea.cards.Models.Card;

public class CardSpec {

    public static final String NAME = "name";
    public static final String COLOR = "color";
    public static final String STATUS = "status";
    public static final String CREATEDBY = "createdBy";
    public static final String CREATEDDATE = "createDate";

    private CardSpec() {
        // empty
    }

    public static Specification<Card> filterBy(CardFilter cardFilter) {
        return Specification
                .where(hasUser(cardFilter.createdBy()))
                .and(hasName(cardFilter.name()))
                .and(hasColor(cardFilter.color()))
                .and(hasStatus(cardFilter.status()));
    }

    private static Specification<Card> hasUser(Integer creator) {
        return ((root, query, cb) -> creator == 0 ? cb.conjunction()
                : cb.equal(root.get(CREATEDBY), creator));
    }

    private static Specification<Card> hasName(String name) {
        return ((root, query, cb) -> name == null || name.isEmpty() ? cb.conjunction()
                : cb.equal(root.get(NAME), name));
    }

    private static Specification<Card> hasColor(String color) {
        return ((root, query, cb) -> color == null || color.isEmpty() ? cb.conjunction()
                : cb.equal(root.get(COLOR), color));
    }

    private static Specification<Card> hasStatus(String status) {
        return ((root, query, cb) -> status == null || status.isEmpty() ? cb.conjunction()
                : cb.equal(root.get(STATUS), status));
    }

}
