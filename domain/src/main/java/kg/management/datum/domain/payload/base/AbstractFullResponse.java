package kg.management.datum.domain.payload.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractFullResponse<PK> extends AbstractFullTimeResponse<PK> {
    protected String createdBy;
    protected String updatedBy;
    protected String deletedBy;
}
