package vn.minhduc.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String getAdminPage(){
        return "admin/dashboard/layout";
    }
}
