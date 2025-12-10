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
@Schema(name = "EmailResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailResponse extends AbstractFullTimeResponse<Long> {
    @Schema(example = "john.doe@example.com")
    String address;
}
