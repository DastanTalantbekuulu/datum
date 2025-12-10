package kg.management.datum.domain.payload.contract.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(name = "PhoneRecordRequest", description = "Request payload for phone number details")
public record PhoneRecordRequest(

        @Schema(description = "Phone number in International E.164 format",
                example = "+996555123456",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Phone number cannot be empty")
        @Size(min = 8, max = 20, message = "Phone number length is invalid")
        @Pattern(regexp = "^\\+[1-9]\\d{6,14}$", message = "Phone number must be in E.164 format (e.g. +996555123456)")
        String number,

        @Schema(description = "Indicates if the phone number is registered with WhatsApp",
                defaultValue = "false",
                example = "true",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Boolean hasWhatsapp,

        @Schema(description = "Indicates if the phone number is registered with Telegram",
                defaultValue = "false",
                example = "false",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Boolean hasTelegram
) {
    public PhoneRecordRequest {
        if (hasWhatsapp == null) hasWhatsapp = false;
        if (hasTelegram == null) hasTelegram = false;
    }
}