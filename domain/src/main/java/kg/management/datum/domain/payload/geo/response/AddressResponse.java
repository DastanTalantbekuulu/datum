package kg.management.datum.domain.payload.geo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import kg.management.datum.domain.entity.base.GeoCoordinates;
import kg.management.datum.domain.payload.base.AbstractFullResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AddressResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResponse extends AbstractFullResponse<Long> {

    private CityResponse city;

    private StreetResponse street;

    private String building;

    private String apartment;

    private String floor;

    private String entrance;

    private String postalCode;

    private String comment;

    @Embedded
    @Builder.Default
    private GeoCoordinates coordinates = new GeoCoordinates();
}