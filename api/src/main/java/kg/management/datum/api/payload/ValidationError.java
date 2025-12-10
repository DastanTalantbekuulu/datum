package kg.management.datum.api.payload;

import lombok.Builder;

@Builder
public record ValidationError(
        String field,
        String message
) {
}
