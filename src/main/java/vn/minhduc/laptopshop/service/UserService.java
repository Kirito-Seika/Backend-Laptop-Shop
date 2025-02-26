package vn.minhduc.laptopshop.service;

import org.springframework.stereotype.Service;
import vn.minhduc.laptopshop.domain.User;
import vn.minhduc.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String handleHomePage() {
        return "Hello World";
    }

    public User handleSaveUser(User user) {
        return this.userRepository.save(user);
    }
}
