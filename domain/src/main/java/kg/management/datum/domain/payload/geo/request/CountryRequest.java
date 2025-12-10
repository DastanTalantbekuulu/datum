package kg.management.datum.domain.payload.geo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import kg.management.datum.domain.validation.ContainsLanguages;
import lombok.Builder;

import java.util.Map;

@Builder
@Schema(name = "CountryRequest", description = "Request payload for creating or updating a Country")
public record CountryRequest(

        @Schema(description = "ISO 3166-1 Alpha-2 code (2 uppercase letters). Must be unique",
                example = "KG",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "ISO Alpha-2 code cannot be blank")
        @Pattern(regexp = "^[A-Z]{2}$", message = "ISO Alpha-2 code must be 2 uppercase letters")
        String isoAlpha2,

        @Schema(description = "ISO 3166-1 Alpha-3 code (3 uppercase letters). Must be unique",
                example = "KGZ",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "ISO Alpha-3 code cannot be blank")
        @Pattern(regexp = "^[A-Z]{3}$", message = "ISO Alpha-3 code must be 3 uppercase letters")
        String isoAlpha3,

        @Schema(description = "Flag indicating if the country is active and available for use",
                example = "true",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "active status cannot be null")
        Boolean active,

        @Schema(description = "Map for internationalization of the country's name. Must contain 'en', 'kg', and 'ru' keys",
                example = "{\"en\": \"Kyrgyzstan\", \"kg\": \"Кыргызстан\", \"ru\": \"Кыргызстан\"}",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "i18n map cannot be null")
        @ContainsLanguages
        Map<String, String> i18n
) {
    public CountryRequest {
        if (active == null) {
            active = true;
        }
    }
}