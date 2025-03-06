package vn.minhduc.laptopshop.validator.product.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.minhduc.laptopshop.validator.product.annotation.ValidPrice;

public class PriceValidator implements ConstraintValidator<ValidPrice, Double> {
    @Override
    public void initialize(ValidPrice constraintAnnotation) {}

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value <= 0) { // Kiểm tra giá trị phải > 0
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Giá sản phẩm phải lớn hơn 0")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
