package com.logicea.cards.Controllers;

import org.springframework.data.domain.Page;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.logicea.cards.DTOs.CardCreateRequest;
import com.logicea.cards.DTOs.CardUpdateRequest;
import com.logicea.cards.DTOs.ErrorDto;
import com.logicea.cards.Models.Card;
import com.logicea.cards.Services.CardsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;

@RestController
@RequestMapping(path = "api/v1")
@Tag(name = "Cards")
@RequiredArgsConstructor
@CrossOrigin
public class CardsController {

        private final CardsService cardsService;

        @Operation(description = "Application users  have a role (Member or Admin) and use a password to authenticate themselves and access to cards they created and admins have access to all cards", summary = "Get paginated and sorted user's cards.", responses = {
                        @ApiResponse(description = "Success", responseCode = "200"),
                        @ApiResponse(description = "Unauthorized/Invalid token", responseCode = "403", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorDto.class))))
        })
        @GetMapping("/cards")
        public ResponseEntity<Page<Card>> getPaginatedCards(
                        @RequestParam int page,
                        @RequestParam int pageSize,
                        @RequestParam @Nullable String sortby) {

                return ResponseEntity.ok(cardsService.GetPaginatedCards(page, pageSize, sortby));
        }

        @Operation(description = "Get paginated endpoint for users", summary = "This is a summary for get paginated user endpoint", responses = {
                        @ApiResponse(description = "Success", responseCode = "200"),
                        @ApiResponse(description = "Unauthorized/Invalid token", responseCode = "403", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorDto.class))))
        })

        @GetMapping("/card")
        public ResponseEntity<Card> getCard(
                        @RequestParam int cardId) {

                return ResponseEntity.ok(cardsService.GetCard(cardId));
        }

        @Operation(description = "Get paginated endpoint for users", summary = "This is a summary for get paginated user endpoint", responses = {
                        @ApiResponse(description = "Success", responseCode = "200"),
                        @ApiResponse(description = "Unauthorized/Invalid token", responseCode = "403", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorDto.class)))),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorDto.class))))
        })
        @PostMapping("/card")
        public void create_card(@Valid @RequestBody CardCreateRequest request) {
                cardsService.addNewCard(request);
        }

        @Operation(description = "A user can delete a card they have access to", summary = "A user can delete a card they have access to", responses = {
                        @ApiResponse(description = "Success", responseCode = "200"),
                        @ApiResponse(description = "Unauthorized/Invalid token", responseCode = "403", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorDto.class))))
        })

        @DeleteMapping("/card")
        public void delete_card(@RequestParam(name = "cardId") Integer cardId) {
                cardsService.deleteCard(cardId);
        }

        @Operation(description = "A user can update the name, the description, the color and/or the status of a card they have access to, Contents of the description and color fields can be cleared out and Available statuses are To Do, In Progress and Done", summary = "A user can update a card.", responses = {
                        @ApiResponse(description = "Success", responseCode = "200"),
                        @ApiResponse(description = "Unauthorized/Invalid token", responseCode = "403", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorDto.class))))
        })

        @PutMapping("/card")
        public ResponseEntity<Card> update_card(
                        @RequestParam("cardId") Integer cardId, @RequestBody CardUpdateRequest request) {

                return ResponseEntity.ok(cardsService.updateCard(cardId, request));
        }

        @Operation(description = "A user can search through cards they have access to Filters include name, color, status and date of creation, Optionally limit results using page & size or offset & limit options and Results may be sorted by name, color, status, date of creation ", summary = "A user can search through the cards they have access.", responses = {
                        @ApiResponse(description = "Success", responseCode = "200"),
                        @ApiResponse(description = "Unauthorized/Invalid token", responseCode = "403", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorDto.class))))
        })
        @GetMapping("/cards-search")
        public ResponseEntity<Page<Card>> search(
                        @RequestParam @Nullable String name,
                        @RequestParam @Nullable String color,
                        @RequestParam @Nullable String status,
                        @RequestParam @Nullable LocalDateTime creationDate,
                        @RequestParam int page,
                        @RequestParam int pageSize,
                        @RequestParam @Nullable String sortBy) {

                return ResponseEntity.ok(
                                cardsService.searchCards(name, color, status, creationDate, page, pageSize, sortBy));
        }

}
