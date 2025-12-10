package kg.management.datum.api.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import kg.management.datum.api.payload.ValidationError;
import kg.management.datum.api.payload.code.StandardCode;
import kg.management.datum.api.payload.error.CommonError;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppResponse<T> {
    private final boolean success;
    private final String code;
    private final String message;
    private final T data;
    private final List<ValidationError> errors;
    @Builder.Default
    private final Instant timestamp = Instant.now();
    @Setter
    private String path;

    public static <T> AppResponse<T> success(T data) {
        return AppResponse.<T>builder()
                .success(true)
                .code(StandardCode.SUCCESS.getCode())
                .message(CommonError.SUCCESS.getMessageKey())
                .data(data)
                .build();
    }
}