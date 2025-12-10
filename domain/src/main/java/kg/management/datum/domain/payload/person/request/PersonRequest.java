package kg.management.datum.domain.payload.person.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.validation.GenderCode;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Schema(name = "PersonRequest", description = "Request payload for creating or updating a Person")
public record PersonRequest(

        @Schema(description = "ID of the user account linked to this person. Optional",
                example = "101",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Positive(message = "User ID must be a positive number")
        Long userId,

        @Schema(description = "Last name of the person",
                example = "Ivanov",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Last name cannot be blank")
        @Size(max = 100, message = "Last name is too long (max 100 characters)")
        String lastName,

        @Schema(description = "First name of the person",
                example = "Ivan",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "First name cannot be blank")
        @Size(max = 100, message = "First name is too long (max 100 characters)")
        String firstName,

        @Schema(description = "Middle name or patronymic of the person",
                example = "Ivanovich",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 100, message = "Middle name is too long (max 100 characters)")
        String middleName,

        @Schema(description = "Date of birth of the person",
                example = "1990-05-15",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Birth date cannot be null")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate birthDate,

        @Schema(description = "Gender code. Must be one of: 'M', 'F', or '<'",
                example = "M",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Gender cannot be blank")
        @GenderCode
        String gender,

        @Schema(description = "ID of the country of citizenship. Optional",
                example = "117",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Positive(message = "Citizenship country ID must be a positive number")
        Long citizenshipId
) {
}