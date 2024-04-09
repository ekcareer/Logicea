package com.logicea.cards.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardCreateRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;
    private String description;
    private String color;

}
