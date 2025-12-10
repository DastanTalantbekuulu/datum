package kg.management.datum.domain.payload.dictionary.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.validation.ContainsLanguages;
import lombok.Builder;

import java.util.Map;

@Builder
@Schema(name = "LanguageRequest", description = "Request payload for creating or updating a Language")
public record LanguageRequest(

        @Schema(description = "ISO 639-1 language code (2 letters). Must be unique",
                example = "kg",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Language code cannot be blank")
        @Size(min = 2, max = 2, message = "The language code must be in ISO 639-1 format (2 letters)")
        @Pattern(regexp = "^[a-z]{2}$", message = "Language code must be a 2-letter lowercase code (e.g., 'en', 'ru')")
        String language,

        @Schema(description = "Native name of the language (e.g., 'Кыргызча', 'Русский')",
                example = "Кыргызча",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Native name cannot be blank")
        @Size(max = 50, message = "Native name is too long (max 50 characters)")
        String nativeName,

        @Schema(description = "Map for internationalization of the language's name. Must contain 'en', 'kg', and 'ru' keys with corresponding translations",
                example = "{\"en\": \"Kyrgyz\", \"kg\": \"Кыргызча\", \"ru\": \"Киргизский\"}",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "i18n map cannot be null")
        @ContainsLanguages
        Map<String, String> i18n
) {
}