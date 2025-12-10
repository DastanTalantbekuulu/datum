package kg.management.datum.domain.payload.person.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(name = "PersonPhotoRequest", description = "Request payload for linking a photo to a person")
public record PersonPhotoRequest(

        @Schema(description = "ID of the file obtained from the file upload endpoint",
                example = "55123",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Storage file ID cannot be null")
        @Positive(message = "Storage file ID must be a positive number")
        Long storageFileId,

        @Schema(description = "Type of the photo (e.g., 'AVATAR', 'FULL_BODY'). Must be a valid photo type code",
                example = "AVATAR",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Photo type cannot be blank")
        @Size(max = 50, message = "Photo type is too long (max 50 characters)")
        @Pattern(regexp = "^[A-Z_]+$", message = "Photo type must contain only uppercase letters and underscores")
        String type,

        @Schema(description = "Flag to set this photo as the primary one of its type for the person",
                example = "true",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "primary flag cannot be null")
        Boolean primary
) {
    public PersonPhotoRequest {
        if (primary == null) {
            primary = false;
        }
    }
}