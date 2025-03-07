package vn.minhduc.laptopshop.validator.register.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Service;
import vn.minhduc.laptopshop.domain.dto.RegisterDTO;
import vn.minhduc.laptopshop.service.UserService;
import vn.minhduc.laptopshop.validator.register.annotation.ValidCheckMail;

@Service
public class CheckMailValidator implements ConstraintValidator<ValidCheckMail, RegisterDTO> {
    private final UserService userService;

    public CheckMailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(RegisterDTO registerDTO, ConstraintValidatorContext context) {

        if (this.userService.checkEmailExist(registerDTO.getEmail())) {
            context.buildConstraintViolationWithTemplate("Email đã tồn tại")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }
        return true;
    }
}
