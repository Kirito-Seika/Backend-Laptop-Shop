package vn.minhduc.laptopshop.validator.product.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.minhduc.laptopshop.validator.product.annotation.ValidQuantity;

public class QuantityValidator implements ConstraintValidator<ValidQuantity, Long> {
    @Override
    public void initialize(ValidQuantity constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value <= 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Số lượng phẩm phải lớn hơn 0").addConstraintViolation();
            return false;
        }
        return true;
    }
}
