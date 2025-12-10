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
@Schema(name = "PhotoTypeRequest", description = "Request payload for creating or updating a Photo Type")
public record PhotoTypeRequest(

        @Schema(description = "Unique code for the photo type. Must be uppercase letters and underscores",
                example = "FULL_BODY",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Code cannot be blank")
        @Size(max = 50, message = "Code must be up to 50 characters long")
        @Pattern(regexp = "^[A-Z_]+$", message = "Code must contain only uppercase letters and underscores")
        String code,

        @Schema(description = "Technical description of the photo type (e.g., for biometric matching).",
                example = "A full-length photograph of the person",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 255, message = "Description is too long (max 255 characters)")
        String description,

        @Schema(description = "Map for internationalization of the photo type's name. Must contain 'en', 'kg', and 'ru' keys",
                example = "{\"en\": \"Full Body\", \"kg\": \"Толук бою\", \"ru\": \"В полный рост\"}",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "i18n map cannot be null")
        @ContainsLanguages
        Map<String, String> i18n
) {
}
