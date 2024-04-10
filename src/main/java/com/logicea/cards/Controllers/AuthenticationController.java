package com.logicea.cards.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logicea.cards.DTOs.AuthenticationRequest;
import com.logicea.cards.DTOs.AuthenticationResponse;
import com.logicea.cards.DTOs.ErrorDto;
import com.logicea.cards.Services.AuthenticationService;

import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;

@RestController
@Tag(name = "Authentication")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService service;

    @Operation(description = "A user can login to get the access token using email and password", summary = "A user can login and access the JWT access by providing the email and password", responses = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Unauthorized/Invalid token", responseCode = "403", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorDto.class))))
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));

    }

}
