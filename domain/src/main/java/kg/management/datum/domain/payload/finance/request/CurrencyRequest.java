package kg.management.datum.domain.payload.finance.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.management.datum.domain.validation.ContainsLanguages; // Импортируем нашу кастомную аннотацию
import lombok.Builder;

import java.util.Map;

@Builder
@Schema(name = "CurrencyRequest", description = "Request payload for creating or updating a Currency")
public record CurrencyRequest(

        @Schema(description = "ISO 4217 currency code (3 uppercase letters). Must be unique",
                example = "USD",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Currency code cannot be blank")
        @Pattern(regexp = "^[A-Z]{3}$", message = "Currency code must be a 3-letter uppercase code (e.g., 'USD', 'EUR')")
        String code,

        @Schema(description = "Currency symbol (e.g., '$', '€', 'с')",
                example = "$",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Currency symbol cannot be blank")
        @Size(max = 5, message = "Currency symbol is too long (max 5 characters)")
        String symbol,

        @Schema(description = "Number of decimal places for the currency (e.g., 2 for USD, 0 for JPY)",
                example = "2",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Decimals cannot be null")
        @Min(value = 0, message = "Decimals cannot be negative")
        @Max(value = 4, message = "Decimals cannot be more than 4")
        Integer decimals,

        @Schema(description = "Map for internationalization of the currency's name. Must contain 'en', 'kg', and 'ru' keys",
                example = "{\"en\": \"US Dollar\", \"kg\": \"АКШ доллары\", \"ru\": \"Доллар США\"}",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "i18n map cannot be null")
        @ContainsLanguages
        Map<String, String> i18n
) {
}