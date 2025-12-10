package kg.management.datum.domain.payload.dictionary.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.validation.ContainsLanguages;
import lombok.Builder;

import java.util.Map;

@Builder
@Schema(name = "ContactTypeRequest", description = "Request payload for creating or updating a Contact Type")
public record ContactTypeRequest(
        @Schema(description = "Unique type identifier",
                example = "HOME",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Type cannot be empty")
        @Size(max = 20, message = "Type must be up to 20 characters long")
        String type,

        @Schema(description = "Map for internationalization. Must contain 'en', 'kg', and 'ru' keys with corresponding translations",
                example = "{\"en\": \"Home\", \"kg\": \"Үй\", \"ru\": \"Дом\"}",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "i18n map cannot be null")
        @Size(min = 3, message = "i18n map must contain at least 3 languages")
        @ContainsLanguages
        Map<String, String> i18n
) {
}