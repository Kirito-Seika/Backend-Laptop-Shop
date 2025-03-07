package vn.minhduc.laptopshop.validator.register.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Service;
import vn.minhduc.laptopshop.domain.dto.RegisterDTO;
import vn.minhduc.laptopshop.service.UserService;
import vn.minhduc.laptopshop.validator.register.annotation.ValidConfirmPassword;

@Service
public class ConfirmPasswordValidator implements ConstraintValidator<ValidConfirmPassword, RegisterDTO> {
        private final UserService userService;

    public ConfirmPasswordValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(RegisterDTO registerDTO, ConstraintValidatorContext context) {
        if (registerDTO == null) {
            return false; // Nếu đối tượng null, trả về false
        }
        String password = registerDTO.getPassword();
        String confirmPassword = registerDTO.getConfirmPassword();
        if (!password.equals(confirmPassword)) {
            context.buildConstraintViolationWithTemplate("ConfirmPassword không trùng khớp với Password").addPropertyNode("confirmPassword").addConstraintViolation().disableDefaultConstraintViolation();
            return false;
        }
        return true;
    }
}
