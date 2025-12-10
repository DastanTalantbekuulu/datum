package kg.management.datum.domain.payload.geo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.validation.ContainsLanguages;
import lombok.Builder;

import java.util.Map;

@Builder
@Schema(name = "RegionRequest", description = "Request payload for creating or updating a Region")
public record RegionRequest(

        @Schema(description = "ID of the country. Must be a valid, existing country ID.",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Country ID cannot be null")
        @Positive(message = "Country ID must be a positive number")
        @Min(1)
        Long countryId,

        @Schema(description = "Optional region code (e.g., official state or province code)",
                example = "CHUY",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 20, message = "Code is too long (max 20 characters)")
        String code,

        @Schema(description = "Map for internationalization of the region's name. Must contain 'en', 'kg', and 'ru' keys",
                example = "{\"en\": \"Chuy Region\", \"kg\": \"Чүй облусу\", \"ru\": \"Чуйская область\"}",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "i18n map cannot be null")
        @ContainsLanguages
        Map<String, String> i18n
) {
}