package kg.management.datum.domain.payload.contract.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(name = "UserPhoneRequest", description = "Request payload for adding or updating a user's phone contact")
public record UserPhoneRequest(

        @Schema(description = "Phone number in International E.164 format",
                example = "+996555123456",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Phone number cannot be empty")
        @Size(min = 8, max = 20, message = "Phone number length is invalid")
        @Pattern(regexp = "^\\+[1-9]\\d{6,14}$", message = "Phone number must be in E.164 format (e.g. +996555123456)")
        String phone,

        @Schema(description = "Code of the contact type (e.g., MOBILE, WORK, HOME)",
                example = "MOBILE",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 20, message = "Contact type code is too long")
        String contactTypeCode,

        @Schema(description = "Indicates if this is the primary phone number for the user",
                defaultValue = "false",
                example = "true",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Boolean isPrimary
) {
    public UserPhoneRequest {
        if (isPrimary == null) isPrimary = false;
    }
}