package kg.management.datum.domain.payload.dictionary.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.validation.ContainsLanguages;
import kg.management.datum.domain.validation.GenderCode;
import lombok.Builder;

import java.util.Map;

@Builder
@Schema(name = "GenderRequest", description = "Request payload for creating or updating a Gender")
public record GenderRequest(

        @Schema(description = "Unique gender code. Must be one of: 'M', 'F', or '<'",
                example = "F",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Code cannot be blank")
        @Size(max = 1, message = "Code must be a single character")
        @GenderCode
        String code,

        @Schema(description = "Map for internationalization of the full gender name. Must contain 'en', 'kg', and 'ru' keys",
                example = "{\"en\": \"Female\", \"kg\": \"Аял\", \"ru\": \"Женский\"}",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "i18n map cannot be null")
        @ContainsLanguages
        Map<String, String> i18n,

        @Schema(description = "Map for internationalization of the short gender name/abbreviation. Must contain 'en', 'kg', and 'ru' keys",
                example = "{\"en\": \"F\", \"kg\": \"Аял\", \"ru\": \"Ж\"}",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "short_i18n map cannot be null")
        @ContainsLanguages
        Map<String, String> shortI18n
) {
}