package vn.minhduc.laptopshop.validator.product.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.minhduc.laptopshop.validator.product.annotation.ValidName;

public class NameValidator implements ConstraintValidator<ValidName, String> {
    @Override
    public void initialize(ValidName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Tên sản phẩm không được để trống")
                    .addConstraintViolation();
            return false;
        }

        if (value.length() < 3) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Tên sản phẩm phải có ít nhất 3 ký tự")
                    .addConstraintViolation();
            return false;
        }

        if (Character.isDigit(value.charAt(0))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Tên sản phẩm không được bắt đầu bằng chữ số")
                    .addConstraintViolation();
            return false;
        }

        if (!value.matches("^[a-zA-ZàÀáÁảẢãÃạẠâÂầẦấẤẩẨẫẪậẬăĂằẰắẮẳẲẵẴặẶđĐêÊềỀếẾểỂễỄệỆiIìÌíÍỉỈĩĨịỊôÔồỒốỐổỔỗỖộỘơƠớỚờỜởỞỡỠợỢuUùÙúÚủỦũŨụỤưƯừỪứỨửỬữỮựỰ\\s0-9]+$")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Tên sản phầm không được chứa ký tự đặc biệt")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
