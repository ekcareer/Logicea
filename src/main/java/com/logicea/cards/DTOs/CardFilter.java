package com.logicea.cards.DTOs;

import java.time.LocalDateTime;

public record CardFilter(Integer createdBy, String name, String color, String status, LocalDateTime dateCreated) {
}
