package kg.management.datum.domain.payload.geo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@Schema(description = "Request payload for creating or updating an Address")
public record AddressRequest(

        @Schema(description = "ID of the city. Must be a valid, existing city ID",
                example = "123",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "City ID cannot be null")
        @Positive(message = "City ID must be a positive number")
        Long cityId,

        @Schema(description = "ID of the street. Must be a valid, existing street ID within the specified city",
                example = "4567",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Street ID cannot be null")
        @Positive(message = "Street ID must be a positive number")
        Long streetId,

        @Schema(description = "Building number, e.g., '125/1', '15A'",
                example = "42",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @Size(max = 50, message = "Building number is too long (max 50 characters)")
        String building,

        @Schema(description = "Apartment or office number, e.g., 'кв 5', 'офис 101'",
                example = "15",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 20, message = "Apartment number is too long (max 20 characters)")
        String apartment,

        @Schema(description = "Floor number. Can be text like 'Ground' or '1'",
                example = "5",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 10, message = "Floor number is too long (max 10 characters)")
        String floor,

        @Schema(description = "Entrance or porch number",
                example = "2",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 10, message = "Entrance number is too long (max 10 characters)")
        String entrance,

        @Schema(description = "Postal code or zip code",
                example = "720000",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 20, message = "Postal code is too long (max 20 characters)")
        String postalCode,

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

        @Schema(description = "Any additional comments about the address",
                example = "Ring the doorbell 'Supermarket'",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 255, message = "Comment is too long (max 255 characters)")
        String comment
) {
}