package vn.minhduc.laptopshop.validator.user.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Service;
import vn.minhduc.laptopshop.service.UserService;
import vn.minhduc.laptopshop.validator.user.annotation.ValidEmail;

@Service
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private UserService userService;

    public EmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email không được để trống").addConstraintViolation();
            return false;
        }

        if (Character.isDigit(value.charAt(0))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email không được bắt đầu bằng chữ số").addConstraintViolation();
            return false;
        }

        if (!value.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@gmail\\.com$")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email không đúng định dang").addConstraintViolation();
            return false;
        }

        if (userService.checkEmailExist(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email đã tồn tại trong hệ thống").addConstraintViolation();
            return false;
        }
        return true;
    }

}
