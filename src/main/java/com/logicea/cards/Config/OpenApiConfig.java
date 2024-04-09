package com.logicea.cards.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Logicea", email = "contact@logicea.com", url = "https://logicea.com"), description = "OpenApi documentation for cards application that allows users to create and manage tasks in the form of cards.", title = "CardApp OpenApi Specification - Logicea", version = "1.0"), security = {
        @SecurityRequirement(name = "bearerAuth") })
@SecurityScheme(name = "bearerAuth", description = "JWT auth description", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {

}
