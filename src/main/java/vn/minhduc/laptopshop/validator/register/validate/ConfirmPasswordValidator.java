package vn.minhduc.laptopshop.validator.register.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.minhduc.laptopshop.domain.dto.RegisterDTO;
import vn.minhduc.laptopshop.validator.register.annotation.ValidConfirmPassword;

public class ConfirmPasswordValidator implements ConstraintValidator<ValidConfirmPassword, RegisterDTO> {

    @Override
    public boolean isValid(RegisterDTO registerDTO, ConstraintValidatorContext context) {
        if (registerDTO == null) {
            return false; // Nếu đối tượng null, trả về false
        }
        String password = registerDTO.getPassword();
        String confirmPassword = registerDTO.getConfirmPassword();

        if (!password.equals(confirmPassword)) {
            context.buildConstraintViolationWithTemplate("Passwords must match")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }

        return true;
    }
}
