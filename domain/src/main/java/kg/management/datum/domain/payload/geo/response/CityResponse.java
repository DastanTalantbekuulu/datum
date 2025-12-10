package kg.management.datum.domain.payload.geo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import kg.management.datum.domain.entity.base.GeoCoordinates;
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
@Schema(name = "CityResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityResponse extends AbstractI18nResponse<Long> {

    private CountryResponse country;

    private RegionResponse region;

    @Builder.Default
    private boolean active = true;

    @Embedded
    @Builder.Default
    private GeoCoordinates coordinates = new GeoCoordinates();
}