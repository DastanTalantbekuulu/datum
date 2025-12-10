package kg.management.datum.domain.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;

public class GenderCodeValidator implements ConstraintValidator<GenderCode, String> {

    private static final Set<String> VALID_CODES = Set.of("M", "F", "<");

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        if (code == null) {
            return true;
        }
        return VALID_CODES.contains(code);
    }
}