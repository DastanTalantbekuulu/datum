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
@Schema(name = "RelationTypeRequest", description = "Request payload for creating or updating a Relation Type")
public record RelationTypeRequest(

        @Schema(description = "Unique code for the relation type. Must be uppercase letters and underscores",
                example = "MOTHER",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Code cannot be blank")
        @Size(max = 50, message = "Code must be up to 50 characters long")
        @Pattern(regexp = "^[A-Z_]+$", message = "Code must contain only uppercase letters and underscores")
        String code,

        @Schema(description = "Map for internationalization of the relation type's name. Must contain 'en', 'kg', and 'ru' keys",
                example = "{\"en\": \"Mother\", \"kg\": \"Апа\", \"ru\": \"Мать\"}",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "i18n map cannot be null")
        @ContainsLanguages
        Map<String, String> i18n
) {
}
