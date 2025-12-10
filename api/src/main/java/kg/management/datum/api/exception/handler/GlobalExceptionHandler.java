package kg.management.datum.api.exception.handler;

import kg.management.datum.api.exception.AppException;
import kg.management.datum.api.payload.ValidationError;
import kg.management.datum.api.payload.factory.ResponseFactory;
import kg.management.datum.api.payload.response.AppResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
@NullMarked
public class GlobalExceptionHandler {

    private final ResponseFactory responseFactory;

    @ExceptionHandler(AppException.class)
    public ResponseEntity<AppResponse<Void>> handleBusinessException(AppException ex) {
        AppResponse<Void> response = responseFactory.createError(ex.getError());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        List<ValidationError> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> new ValidationError(err.getField(), err.getDefaultMessage()))
                .toList();
        return ResponseEntity.badRequest()
                .body(responseFactory.createValidationError(errors));
    }
}