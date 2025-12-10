package kg.management.datum.domain.payload.person.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import kg.management.datum.domain.payload.base.AbstractFullResponse;
import kg.management.datum.domain.payload.dictionary.response.GenderResponse;
import kg.management.datum.domain.payload.geo.response.CountryResponse;
import kg.management.datum.domain.payload.user.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "PersonResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonResponse extends AbstractFullResponse<Long> {

    private UserResponse user;

    private String lastName;

    private String firstName;

    private String middleName;

    private LocalDate birthDate;

    private GenderResponse gender;

    private CountryResponse citizenship;
}
