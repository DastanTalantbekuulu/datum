package kg.management.datum.domain.payload.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractFullTimeResponse<PK> extends AbstractCreatedAtResponse<PK> {
    protected Instant updatedAt;
    protected Instant deletedAt;
}
