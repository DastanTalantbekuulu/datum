package kg.management.datum.domain.payload.geo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import kg.management.datum.domain.payload.base.AbstractI18nResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "RegionResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionResponse extends AbstractI18nResponse<Long> {

    private CountryResponse country;

    private String code;
}
