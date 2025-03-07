package vn.minhduc.laptopshop.validator.user.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.minhduc.laptopshop.validator.user.annotation.ValidPhone;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {

    @Override
    public void initialize(ValidPhone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Số điện thoại không được để trống").addConstraintViolation();
            return false;
        }

        if (!value.matches("^[0-9]+$")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Số điện thoại chỉ được chứa các chữ số").addConstraintViolation();
            return false;
        }

        if (value.length() < 10) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Số điện thoại phải có ít nhất 10 chữ số").addConstraintViolation();
            return false;
        }
        return true;
    }
}
