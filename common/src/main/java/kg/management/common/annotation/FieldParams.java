package kg.management.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FieldParams {
    String label() default "";
    boolean hidden() default false;
    String typeOverride() default "";
}