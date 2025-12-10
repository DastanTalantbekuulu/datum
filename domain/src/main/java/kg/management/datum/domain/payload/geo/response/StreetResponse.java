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
@Schema(name = "StreetResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StreetResponse extends AbstractI18nResponse<Long> {

    private CityResponse city;

    private String externalCode;

}
