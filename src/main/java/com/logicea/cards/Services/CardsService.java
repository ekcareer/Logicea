package com.logicea.cards.Services;

import java.util.Objects;

import org.apache.el.stream.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Sort;
import com.logicea.cards.DTOs.CardCreateRequest;
import com.logicea.cards.DTOs.CardFilter;
import com.logicea.cards.DTOs.CardUpdateRequest;
import com.logicea.cards.DTOs.GetAllCardFilter;
import com.logicea.cards.Models.Card;
import com.logicea.cards.Models.CardStatus;
import com.logicea.cards.QuerySpecs.CardSpec;
import com.logicea.cards.QuerySpecs.GetAllCardSpec;
import com.logicea.cards.Repositories.CardRepository;

import java.time.LocalDateTime;

import jakarta.transaction.Transactional;

@Service
public class CardsService {

    private final CardRepository cardRepository;
    private final AuthenticationService authenticationService;
    private ModelMapper modelMapper;

    @Autowired
    public CardsService(CardRepository cardRepository, AuthenticationService authenticationService,
            ModelMapper modelMapper) {
        this.cardRepository = cardRepository;
        this.authenticationService = authenticationService;
        this.modelMapper = modelMapper;
    }

    public Page<Card> GetPaginatedCards(int pageNumber, int pageSize, String sort) {

        Pageable pageable = null;

        if (sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        Specification<Card> spec = GetAllCardSpec
                .filterBy(new GetAllCardFilter(authenticationService.getCurrentUser()));

        Page<Card> result = cardRepository.findAll(spec, pageable);

        return result;
    }

    public void addNewCard(CardCreateRequest request) {

        Card fetchedCard = null;

        if (authenticationService.getCurrentUser() != 0) {

            fetchedCard = cardRepository.findByNameAndCreatedBy(request.getName(),
                    authenticationService.getCurrentUser());

        } else {
            fetchedCard = cardRepository.findByName(request.getName());
        }

        if (fetchedCard != null)
            throw new IllegalStateException("Card already exist!!");

        Card card = modelMapper.map(request, Card.class);

        card.setStatus(CardStatus.TODO);

        cardRepository.save(card);
    }

    public void deleteCard(Integer cardId) {

        boolean exists;

        if (authenticationService.getCurrentUser() != 0) {

            exists = cardRepository.existsByIdAndCreatedBy(cardId, authenticationService.getCurrentUser());
        } else {
            exists = cardRepository.existsById(cardId);
        }

        if (!exists) {
            throw new IllegalStateException(
                    "Card with id " + cardId + "does not exists");
        }
        cardRepository.deleteById(cardId);
    }

    @Transactional
    public void updateCard(Integer cardId, CardUpdateRequest request) {

        Card fetchedCard = GetCardByUserRole(cardId);

        if (request.getName() != null &&
                request.getName().length() > 0 &&
                !Objects.equals(fetchedCard.getName(), request.getName())) {

            fetchedCard.setName(request.getName());
        }

        if (request.getDescription() != null &&
                request.getDescription().length() > 0 &&
                !Objects.equals(fetchedCard.getDescription(), request.getDescription())) {

            fetchedCard.setDescription(request.getDescription());
        }

        if (request.getColor() != null &&
                request.getColor().length() > 0 &&
                !Objects.equals(fetchedCard.getColor(), request.getColor())) {

            fetchedCard.setColor(request.getColor());
        }
        if (request.getStatus() != null &&
                !Objects.equals(fetchedCard.getStatus(), request.getStatus())) {

            fetchedCard.setStatus(request.getStatus());
        }
    }

    public Page<Card> searchCards(String name, String color, String status, LocalDateTime creationDate, int pageNumber,
            int pageSize, String sort) {

        Pageable pageable = null;

        if (sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        Specification<Card> spec = CardSpec
                .filterBy(new CardFilter(authenticationService.getCurrentUser(), name, color, status, creationDate));

        return cardRepository.findAll(spec, pageable);
    }

    public Card GetCard(int cardId) {

        return GetCardByUserRole(cardId);
    }

    private Card GetCardByUserRole(Integer cardId) {

        Card fetchedCard = null;

        if (authenticationService.getCurrentUser() != 0) {

            fetchedCard = cardRepository.findByIdAndCreatedBy(cardId, authenticationService.getCurrentUser());
        } else {
            fetchedCard = cardRepository.findById(cardId).orElseThrow();
        }

        return fetchedCard;
    }

}
