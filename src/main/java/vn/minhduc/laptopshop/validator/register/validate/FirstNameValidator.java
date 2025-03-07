package vn.minhduc.laptopshop.validator.register.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.minhduc.laptopshop.validator.register.annotation.ValidFirstName;

public class FirstNameValidator implements ConstraintValidator<ValidFirstName, String> {
    @Override
    public void initialize(ValidFirstName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("FirstName không được để trống")
                    .addConstraintViolation();
            return false;
        }

        if (value.length() < 6) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("FirstName phải có ít nhất 6 ký tự")
                    .addConstraintViolation();
            return false;
        }

        if (Character.isDigit(value.charAt(0))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("FirstName không được bắt đầu bằng chữ số")
                    .addConstraintViolation();
            return false;
        }

        if (!value.matches("^[a-zA-ZàÀáÁảẢãÃạẠâÂầẦấẤẩẨẫẪậẬăĂằẰắẮẳẲẵẴặẶđĐêÊềỀếẾểỂễỄệỆiIìÌíÍỉỈĩĨịỊôÔồỒốỐổỔỗỖộỘơƠớỚờỜởỞỡỠợỢuUùÙúÚủỦũŨụỤưƯừỪứỨửỬữỮựỰ\\s0-9]+$")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("FirstName không được chứa ký tự đặc biệt")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
