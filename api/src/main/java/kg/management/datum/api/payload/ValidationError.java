package kg.management.datum.api.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record ValidationError(
        @Getter String field,
        @Getter String message
) {
}
