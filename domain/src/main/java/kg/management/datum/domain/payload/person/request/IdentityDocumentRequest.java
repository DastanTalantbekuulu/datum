package kg.management.datum.domain.payload.person.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.validation.GenderCode;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Schema(name = "IdentityDocumentRequest", description = "Request payload for creating or updating an Identity Document")
public record IdentityDocumentRequest(

        @Schema(description = "Type of the document (e.g., 'ID', 'PS' for Passport)",
                example = "ID",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Document type cannot be blank")
        @Size(max = 2, message = "Document type code is too long (max 2 characters)")
        String documentType,

        @Schema(description = "Unique document number",
                example = "AN1234567",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Document number cannot be blank")
        @Size(max = 50, message = "Document number is too long (max 50 characters)")
        String docNumber,

        @Schema(description = "Document serial number (optional)",
                example = "123",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 20, message = "Serial number is too long (max 20 characters)")
        String serial,

        @Schema(description = "Issuing authority (optional)",
                example = "State Registration Service",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 255, message = "Issuing authority is too long (max 255 characters)")
        String authority,

        @Schema(description = "Date when the document was issued",
                example = "2020-01-15",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Issue date cannot be null")
        LocalDate issueDate,

        @Schema(description = "ID of the country that issued the document",
                example = "117",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Issuing country ID cannot be null")
        @Positive(message = "Issuing country ID must be a positive number")
        Long issuingCountryId,

        @Schema(description = "Machine Readable Zone - Line 1",
                example = "P<UTOERIKSSON<<ANNA<MARIA<<<<<<<<<<<<<<<<<<<",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 44, message = "MRZ string 1 is too long (max 44 characters)")
        String mrzString1,

        @Schema(description = "Machine Readable Zone - Line 2",
                example = "L898902C36UTO7408122F1204159<<<<<<<<<<<<<<04",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 44, message = "MRZ string 2 is too long (max 44 characters)")
        String mrzString2,

        @Schema(description = "Machine Readable Zone - Line 3",
                example = "D231458907UTO7408122F1204159<<<<<<<<<<<<<<<6",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 30, message = "MRZ string 3 is too long (max 30 characters)")
        String mrzString3,

        @Schema(description = "Surname from MRZ",
                example = "ERIKSSON",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 100, message = "MRZ surname is too long (max 100 characters)")
        String mrzSurname,

        @Schema(description = "Given names from MRZ",
                example = "ANNA MARIA",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 100, message = "MRZ given names are too long (max 100 characters)")
        String mrzGivenNames,

        @Schema(description = "Document number from MRZ",
                example = "L898902C3",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 20, message = "MRZ document number is too long (max 20 characters)")
        String mrzDocNumber,

        @Schema(description = "Birth date from MRZ",
                example = "1974-08-12",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        LocalDate mrzBirthDate,

        @Schema(description = "Expiry date from MRZ",
                example = "2041-12-04",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "MRZ expiry date cannot be null")
        LocalDate mrzExpiryDate,

        @Schema(description = "Personal number from MRZ",
                example = "D23145890",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Size(max = 20, message = "MRZ personal number is too long (max 20 characters)")
        String mrzPersonalNumber,

        @Schema(description = "Sex code from MRZ. Must be one of: 'M', 'F', or '<'.",
                example = "F",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @GenderCode
        String mrzSexCode,

        @Schema(description = "ID of the nationality from MRZ",
                example = "117",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Positive(message = "MRZ nationality ID must be a positive number")
        Long mrzNationalityId,

        @Schema(description = "Flag to set this document as the primary one for the person",
                example = "true",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "primary flag cannot be null")
        Boolean primary,

        @Schema(description = "Flag indicating if the document is currently active",
                example = "true",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "active flag cannot be null")
        Boolean active
) {
    public IdentityDocumentRequest {
        if (primary == null) {
            primary = false;
        }
        if (active == null) {
            primary = true;
        }
    }
}