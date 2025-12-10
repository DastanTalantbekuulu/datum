package kg.management.datum.domain.payload.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.UUID;

@Builder
@Schema(name = "UserRequest", description = "Request payload for creating or updating a User")
public record UserRequest(

        @Schema(description = "External unique identifier for the user (e.g., from an external system). Optional",
                example = "f47ac10b-58cc-4372-a567-0e02b2c3d479",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                message = "Invalid UUID format for external ID")
        UUID externalId,

        @Schema(description = "Unique username for the user",
                example = "john Doe",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Username cannot be blank")
        @Size(max = 255, message = "Username is too long (max 255 characters)")
        String username,

        @Schema(description = "User's password. For updates, can be omitted to keep the existing password",
                example = "a-very-secure-password",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 1024, message = "Password is too long (max 1024 characters)")
        String password,

        @Schema(description = "Flag indicating if the user account is enabled",
                example = "true",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Enabled status cannot be null")
        Boolean enabled,

        @Schema(description = "Flag indicating if the user account is locked",
                example = "false",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Locked status cannot be null")
        Boolean locked
) {
    public UserRequest {
        if (enabled == null) enabled = true;
        if (locked == null) locked = false;
    }
}