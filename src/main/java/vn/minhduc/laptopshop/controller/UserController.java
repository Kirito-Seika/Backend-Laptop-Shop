package vn.minhduc.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.minhduc.laptopshop.domain.User;
import vn.minhduc.laptopshop.repository.UserRepository;
import vn.minhduc.laptopshop.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrayUsers = this.userService.getAllUsersByEmail("admin@gmail.com");
        System.out.println(arrayUsers);
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("listUsers", users);
        return "admin/user/layout";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("userId", id);
        return "admin/user/detail";
    }

    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("createUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("createUser") User user) {
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/update/{id}") // GET
    public String getUpdateUserPage(Model model) {
        model.addAttribute("updateUser", new User());
        return "admin/user/update";
    }
}

