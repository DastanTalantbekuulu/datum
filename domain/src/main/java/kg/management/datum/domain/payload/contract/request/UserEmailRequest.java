package kg.management.datum.domain.payload.contract.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(name = "UserEmailRequest", description = "Request payload for adding or updating a user's email contact")
public record UserEmailRequest(

        @Schema(description = "Valid email address",
                example = "john.doe@company.com",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Email address cannot be empty")
        @Size(max = 255, message = "Email address is too long")
        @Pattern(regexp = "^[a-zA-Z0-9.!#$%&*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+$",
                message = "Invalid email format")
        String email,

        @Schema(description = "Code of the contact type (e.g., WORK, HOME, OTHER)",
                example = "WORK",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 20, message = "Contact type code is too long")
        String contactTypeCode,

        @Schema(description = "Indicates if this is the primary email for the user",
                defaultValue = "false",
                example = "true",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Boolean isPrimary
) {
    public UserEmailRequest {
        if (isPrimary == null) isPrimary = false;
    }
}