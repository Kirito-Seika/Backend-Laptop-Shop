package vn.minhduc.laptopshop.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import vn.minhduc.laptopshop.validator.register.annotation.ValidFirstName;
import vn.minhduc.laptopshop.validator.register.annotation.ValidLastName;
import vn.minhduc.laptopshop.validator.register.annotation.ValidConfirmPassword;
import vn.minhduc.laptopshop.validator.user.annotation.ValidEmail;
import vn.minhduc.laptopshop.validator.user.annotation.ValidPhone;
import vn.minhduc.laptopshop.validator.user.annotation.ValidPassword;

@ValidConfirmPassword
public class RegisterDTO {
    @ValidFirstName
    private String firstName;

    @ValidLastName
    private String lastName;

    @ValidEmail
    private String email;

    @ValidPassword
    private String password;
    private String confirmPassword;

    @ValidPhone
    private String phone;

    @NotEmpty(message = "Địa chỉ không được để trống")
    private String address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
