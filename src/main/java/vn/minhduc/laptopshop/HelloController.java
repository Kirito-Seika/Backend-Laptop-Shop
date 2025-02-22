package vn.minhduc.laptopshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

    @GetMapping("/user")
    public String userPage() {
        return "Hello User";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "Hello admin";
    }
}
