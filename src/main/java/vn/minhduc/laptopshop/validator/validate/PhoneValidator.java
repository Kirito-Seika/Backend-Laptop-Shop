package vn.minhduc.laptopshop.validator.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.minhduc.laptopshop.validator.annotation.ValidPhone;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {

    @Override
    public void initialize(ValidPhone constraintAnnotation) {
        // Không cần thực hiện gì đặc biệt trong trường hợp này
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Kiểm tra nếu giá trị là null hoặc trống
        if (value == null || value.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Số điện thoại không được để trống")
                    .addConstraintViolation();
            return false;
        }

        // Kiểm tra số điện thoại chỉ chứa các chữ số
        if (!value.matches("^[0-9]+$")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Số điện thoại chỉ được chứa các chữ số")
                    .addConstraintViolation();
            return false;
        }

        // Kiểm tra chiều dài của số điện thoại phải ít nhất 10 ký tự
        if (value.length() < 10) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Số điện thoại phải có ít nhất 10 chữ số")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
