package kg.management.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ContainsLanguagesValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContainsLanguages {
    String message() default "{validation.map.contains.required.languages}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] value() default {"en", "ky", "ru"};
}