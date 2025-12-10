package kg.management.datum.api.payload.factory;

import kg.management.datum.api.payload.ValidationError;
import kg.management.datum.api.payload.code.StandardCode;
import kg.management.datum.api.payload.error.AppError;
import kg.management.datum.api.payload.error.CommonError;
import kg.management.datum.api.payload.response.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResponseFactory {

    private final MessageSource messageSource;

    public <T> AppResponse<T> success(T data) {
        return AppResponse.<T>builder()
                .success(true)
                .code(StandardCode.SUCCESS.getCode())
                .message(getMessage(CommonError.SUCCESS.getMessageKey()))
                .data(data)
                .build();
    }

    public AppResponse<Void> createError(AppError error, Object... args) {
        String localizedMessage = messageSource.getMessage(
                error.getMessageKey(),
                args,
                error.getMessageKey(),
                LocaleContextHolder.getLocale());

        return AppResponse.<Void>builder()
                .success(false)
                .code(error.getCode())
                .message(localizedMessage)
                .build();
    }

    public AppResponse<Void> createValidationError(List<ValidationError> errors) {
        return AppResponse.<Void>builder()
                .success(false)
                .code(CommonError.VALIDATION_ERROR.getCode())
                .message(getMessage(CommonError.VALIDATION_ERROR.getMessageKey()))
                .errors(errors)
                .build();
    }

    private String getMessage(String key) {
        return messageSource.getMessage(key, null, key, LocaleContextHolder.getLocale());
    }
}