package kg.management.datum.domain.payload.storage.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(description = "Request payload for storing metadata of an uploaded file")
public record StorageFileRequest(

        @Schema(description = "The original name of the file as provided by the user",
                example = "passport_scan_page_1.jpg",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Original name cannot be blank")
        @Size(max = 255, message = "Original name is too long (max 255 characters)")
        String originalName,

        @Schema(description = "The MIME type of the file (e.g., 'image/jpeg', 'application/pdf')",
                example = "image/jpeg",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "MIME type cannot be blank")
        @Size(max = 100, message = "MIME type is too long (max 100 characters)")
        @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9!#$&\\-^_]*\\/[a-zA-Z0-9][a-zA-Z0-9!#$&\\-^_.]*$",
                message = "Invalid MIME type format")
        String mimeType,

        @Schema(description = "The size of the file in bytes",
                example = "1048576",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "File size cannot be null")
        @Positive(message = "File size must be a positive number")
        Long size,

        @Schema(description = "The storage bucket or folder where the file is located",
                example = "user-documents",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Bucket cannot be blank")
        @Size(max = 100, message = "Bucket name is too long (max 100 characters)")
        String bucket,

        @Schema(description = "The unique path or key of the file within the bucket",
                example = "2023/10/27/uuid-123e4567-e89b-12d3-a456-426614174000.jpg",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Path cannot be blank")
        @Size(max = 512, message = "Path is too long (max 512 characters)")
        String path,

        @Schema(description = "Flag indicating if the file is publicly accessible",
                example = "false",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "is_public flag cannot be null")
        Boolean isPublic
) {
    public StorageFileRequest {
        if (isPublic == null) isPublic = false;
    }
}