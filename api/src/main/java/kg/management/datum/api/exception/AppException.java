package kg.management.datum.api.exception;

import kg.management.datum.api.payload.error.AppError;
import lombok.Getter;

@Getter
public abstract class AppException extends RuntimeException {

    private final AppError error;
    private final Object[] args;

    public AppException(AppError error) {
        super(error.getMessageKey());
        this.error = error;
        args = null;
    }

    public AppException(AppError error, Object... args) {
        super(error.getMessageKey());
        this.error = error;
        this.args = args;
    }

    public AppException(AppError error, Throwable cause) {
        super(error.getMessageKey(), cause);
        this.error = error;
        args = null;
    }

    public AppException(AppError error, Throwable cause, Object... args) {
        super(error.getMessageKey(), cause);
        this.error = error;
        this.args = args;
    }
}