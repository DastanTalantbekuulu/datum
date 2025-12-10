package kg.management.datum.domain.payload.geo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kg.management.datum.domain.validation.ContainsLanguages;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Map;

@Builder
@Schema(description = "Request payload for creating or updating a City")
public record CityRequest(

        @Schema(description = "ID of the country. Must be a valid, existing country ID.",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Country ID cannot be null")
        @Positive(message = "Country ID must be a positive number")
        Long countryId,

        @Schema(description = "ID of the region. Optional. Must be a valid region ID within the specified country",
                example = "25",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Positive(message = "Region ID must be a positive number")
        Long regionId,

        @Schema(description = "Geographic latitude. Must be between -90 and 90",
                example = "42.874621",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @DecimalMin(value = "-90.0", message = "Latitude must be -90 or greater")
        @DecimalMax(value = "90.0", message = "Latitude must be 90 or less")
        BigDecimal latitude,

        @Schema(description = "Geographic longitude. Must be between -180 and 180",
                example = "74.569832",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @DecimalMin(value = "-180.0", message = "Longitude must be -180 or greater")
        @DecimalMax(value = "180.0", message = "Longitude must be 180 or less")
        BigDecimal longitude,

        @Schema(description = "Flag indicating if the city is active and available for use",
                example = "true",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "active status cannot be null")
        Boolean active,

        @Schema(description = "Map for internationalization of the city's name. Must contain 'en', 'kg', and 'ru' keys",
                example = "{\"en\": \"Bishkek\", \"kg\": \"Бишкек\", \"ru\": \"Бишкек\"}",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "i18n map cannot be null")
        @ContainsLanguages
        Map<String, String> i18n
) {
    public CityRequest {
        if (active == null) active = false;
    }
}