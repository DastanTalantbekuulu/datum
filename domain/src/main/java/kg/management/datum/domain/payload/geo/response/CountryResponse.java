package kg.management.datum.domain.payload.geo.response;

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
@Schema(name = "CountryResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryResponse extends AbstractI18nResponse<Long> {

    private String isoAlpha2;

    private String isoAlpha3;

    @Builder.Default
    private boolean active = true;

}