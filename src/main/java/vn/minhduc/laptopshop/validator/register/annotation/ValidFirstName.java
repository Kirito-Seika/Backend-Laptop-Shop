package vn.minhduc.laptopshop.validator.register.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vn.minhduc.laptopshop.validator.register.validate.FirstNameValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FirstNameValidator.class)
public @interface ValidFirstName {
    String message() default "First Name không hợp lệ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
