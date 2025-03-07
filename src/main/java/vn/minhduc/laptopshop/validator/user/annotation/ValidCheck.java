package vn.minhduc.laptopshop.validator.user.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vn.minhduc.laptopshop.validator.user.validate.CheckValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCheck {
    String message() default "Password and Confirm Password do not meet the validation criteria";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
