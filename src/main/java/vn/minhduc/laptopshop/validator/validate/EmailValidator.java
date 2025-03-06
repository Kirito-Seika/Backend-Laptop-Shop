package vn.minhduc.laptopshop.validator.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.minhduc.laptopshop.validator.annotation.ValidEmail;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Override
    public void initialize(ValidEmail constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email không được để trống")
                    .addConstraintViolation();
            return false;
        }

        if (Character.isDigit(value.charAt(0))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email không được bắt đầu bằng chữ số")
                    .addConstraintViolation();
            return false;
        }

        if (!value.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@gmail\\.com$")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email không đúng định dang")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}
