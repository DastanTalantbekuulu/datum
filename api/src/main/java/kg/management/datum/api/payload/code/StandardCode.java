package kg.management.datum.api.payload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StandardCode implements ResponseCode {
    SUCCESS("200"),
    FAILURE("500"),
    VALIDATION_ERROR("400");

    private final String code;
}