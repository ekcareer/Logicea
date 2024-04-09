package com.logicea.cards.QuerySpecs;

import org.springframework.data.jpa.domain.Specification;

import com.logicea.cards.DTOs.GetAllCardFilter;
import com.logicea.cards.Models.Card;

public class GetAllCardSpec {

    public static final String CREATEDBY = "createdBy";

    private GetAllCardSpec() {
        // empty
    }

    public static Specification<Card> filterBy(GetAllCardFilter cardFilter) {
        return Specification
                .where(hasUser(cardFilter.createdBy()));
    }

    private static Specification<Card> hasUser(Integer creator) {
        return ((root, query, cb) -> creator == 0 ? cb.conjunction()
                : cb.equal(root.get(CREATEDBY), creator));
    }

}
