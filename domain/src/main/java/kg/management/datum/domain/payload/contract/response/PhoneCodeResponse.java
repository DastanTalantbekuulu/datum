package kg.management.datum.domain.payload.contract.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import kg.management.datum.domain.payload.base.AbstractFullTimeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "PhoneCodeResponse")
public class PhoneCodeResponse extends AbstractFullTimeResponse<Long> {
    private Long countryId;

    private String prefix;

    private String mask;

    private String emoji;

    private String regex;

    private boolean isMain;
}