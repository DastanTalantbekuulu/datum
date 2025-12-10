package kg.management.datum.domain.payload.contract.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(name = "PhoneCodeRequest", description = "Request payload for creating or updating a country phone code")
public record PhoneCodeRequest(
        @Schema(description = "ID of the Country this code belongs to",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Country ID cannot be null")
        Long countryId,

        @Schema(description = "Phone prefix digits (without +)",
                example = "996",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Phone prefix cannot be empty")
        @Size(max = 10, message = "Prefix cannot exceed 10 characters")
        String prefix,

        @Schema(description = "Input mask for frontend formatting",
                example = "(###) ##-##-##",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 50, message = "Mask cannot exceed 50 characters")
        String mask,

        @Schema(description = "Country flag emoji associated with this code",
                example = "🇰🇬",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 5, message = "Emoji cannot exceed 5 characters")
        String emoji,

        @Schema(description = "Regular expression for validation",
                example = "^\\+996\\d{9}$",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String regex,

        @Schema(description = "Indicates if this is the primary code for the country",
                defaultValue = "true",
                example = "true",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Boolean isMain
) {
    public PhoneCodeRequest {
        if (isMain == null) isMain = true;
    }
}