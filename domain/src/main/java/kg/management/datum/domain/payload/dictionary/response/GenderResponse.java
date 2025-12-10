package kg.management.datum.domain.payload.dictionary.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import kg.management.datum.domain.payload.base.AbstractI18nExtendResponse;
import lombok.Data;

@Data
@Schema(name = "GenderResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenderResponse extends AbstractI18nExtendResponse<String> {
}