package kg.management.datum.domain.payload.finance.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import kg.management.datum.domain.payload.base.AbstractI18nResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CurrencyResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyResponse extends AbstractI18nResponse<String> {
    private String symbol;
    @Builder.Default
    private Byte decimals = 2;
}
