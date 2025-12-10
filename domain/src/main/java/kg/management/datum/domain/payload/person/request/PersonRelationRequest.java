package kg.management.datum.domain.payload.person.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(name = "PersonRelationRequest", description = "Request payload for creating a relation between two persons")
public record PersonRelationRequest(

        @Schema(description = "ID of the relative person",
                example = "456",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Relative ID cannot be null")
        @Positive(message = "Relative ID must be a positive number")
        Long relativeId,

        @Schema(description = "Type of the relation (e.g., 'FATHER', 'MOTHER', 'SIBLING'). Must be a valid relation type code",
                example = "MOTHER",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Relation type cannot be blank")
        @Size(max = 50, message = "Relation type is too long (max 50 characters)")
        @Pattern(regexp = "^[A-Z_]+$", message = "Relation type must contain only uppercase letters and underscores")
        String relationType,

        @Schema(description = "Flag indicating if the relation is biological. Defaults to true if not provided",
                example = "true",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Boolean biological
) {
    public PersonRelationRequest {
        if (biological == null) biological = true;
    }
}