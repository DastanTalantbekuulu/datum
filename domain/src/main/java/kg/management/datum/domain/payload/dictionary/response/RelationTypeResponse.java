package kg.management.datum.domain.payload.dictionary.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import kg.management.datum.domain.payload.base.AbstractI18nResponse;
import lombok.Data;

@Data
@Schema(name = "RelationTypeResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelationTypeResponse extends AbstractI18nResponse<String> {
}