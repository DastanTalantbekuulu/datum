package kg.management.datum.domain.payload.contract.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(name = "EmailRequest", description = "Request payload for email address details")
public record EmailRequest(

        @Schema(description = "Valid email address",
                example = "john.doe@example.com",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Email address cannot be empty")
        @Size(max = 255, message = "Email address is too long")
        @Pattern(regexp = "^[a-zA-Z0-9.!#$%&*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+$",
                message = "Invalid email format")
        String address
) {
}