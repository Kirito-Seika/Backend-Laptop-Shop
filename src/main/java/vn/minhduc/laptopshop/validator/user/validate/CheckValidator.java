package vn.minhduc.laptopshop.validator.user.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.minhduc.laptopshop.domain.User;
import vn.minhduc.laptopshop.service.UserService;
import vn.minhduc.laptopshop.validator.user.annotation.ValidCheck;

@Component
public class CheckValidator implements ConstraintValidator<ValidCheck, User> {
    private final UserService userService;

    public CheckValidator() {
        this.userService = null;
    }

    @Autowired
    public CheckValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        if (userService != null && userService.checkEmailExist(user.getEmail())) {
            context.buildConstraintViolationWithTemplate("Email đã tồn tại")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }
        return true;
    }
}
