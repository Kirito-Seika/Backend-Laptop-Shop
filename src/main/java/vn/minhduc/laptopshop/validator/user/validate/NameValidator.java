package vn.minhduc.laptopshop.validator.user.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.minhduc.laptopshop.validator.user.annotation.ValidName;

public class NameValidator implements ConstraintValidator<ValidName, String> {

    @Override
    public void initialize(ValidName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Tên không được để trống").addConstraintViolation();
            return false;
        }

        if (value.length() < 6) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Tên phải có ít nhất 6 ký tự").addConstraintViolation();
            return false;
        }

        if (Character.isDigit(value.charAt(0))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Tên không được bắt đầu bằng chữ số").addConstraintViolation();
            return false;
        }

        if (!value.matches("^[a-zA-ZàÀáÁảẢãÃạẠâÂầẦấẤẩẨẫẪậẬăĂằẰắẮẳẲẵẴặẶđĐêÊềỀếẾểỂễỄệỆiIìÌíÍỉỈĩĨịỊôÔồỒốỐổỔỗỖộỘơƠớỚờỜởỞỡỠợỢuUùÙúÚủỦũŨụỤưƯừỪứỨửỬữỮựỰ\\s0-9]+$")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Tên không được chứa ký tự đặc biệt").addConstraintViolation();
            return false;
        }
        return true;
    }
}
