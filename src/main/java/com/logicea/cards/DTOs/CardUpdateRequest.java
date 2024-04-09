package com.logicea.cards.DTOs;

import com.logicea.cards.Models.CardStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardUpdateRequest {

    private Integer id;
    private String name;
    private String description;
    private String color;

    @Enumerated(value = EnumType.STRING)
    private CardStatus status;

}
