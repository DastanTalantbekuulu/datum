package kg.management.datum.domain.payload.geo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.validation.ContainsLanguages;
import lombok.Builder;

import java.util.Map;

@Builder
@Schema(name = "StreetRequest", description = "Request payload for creating or updating a Street")
public record StreetRequest(

        @Schema(description = "ID of the city. Must be a valid, existing city ID",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "City ID cannot be null")
        @Positive(message = "City ID must be a positive number")
        Long cityId,

        @Schema(description = "Optional external code for the street (e.g., from a government registry)",
                example = "01015000000000100",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 100, message = "External code is too long (max 100 characters)")
        String externalCode,

        @Schema(description = "Map for internationalization of the street's name. Must contain 'en', 'kg', and 'ru' keys",
                example = "{\"en\": \"Manas Avenue\", \"kg\": \"Манас проспекти\", \"ru\": \"Проспект Манаса\"}",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "i18n map cannot be null")
        @ContainsLanguages
        Map<String, String> i18n
) {
}