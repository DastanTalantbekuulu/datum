package kg.management.datum.api.payload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ResponseCode {
    USER_NOT_FOUND("USR_001"),
    EMAIL_ALREADY_EXISTS("USR_002"),
    PASSWORD_TOO_WEAK("USR_003");

    private final String code;
}