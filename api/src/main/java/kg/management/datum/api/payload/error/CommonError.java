package kg.management.datum.api.payload.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonError implements AppError {
    SUCCESS("SUCCESS", "success"),
    VALIDATION_ERROR("VALIDATION_ERROR", "error.validation.failed"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "error.internal.server"),
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "error.resource.not_found");

    private final String code;
    private final String messageKey;
}