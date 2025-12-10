package kg.management.datum.domain.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ContainsLanguagesValidator implements ConstraintValidator<ContainsLanguages, Map<String, String>> {

    private Set<String> set;
    private String languages;

    @Override
    public void initialize(ContainsLanguages constraintAnnotation) {
        set = Arrays.stream(constraintAnnotation.value())
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        languages = set.stream()
                .map(lang -> "'" + lang + "'")
                .collect(Collectors.joining(", "));
    }

    @Override
    public boolean isValid(Map<String, String> i18nMap, ConstraintValidatorContext context) {
        if (i18nMap == null) {
            return true;
        }
        Set<String> set = i18nMap.keySet().stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        boolean isValid = set.containsAll(this.set);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate().replace("{languages}", languages))
                    .addConstraintViolation();
        }
        return isValid;
    }
}